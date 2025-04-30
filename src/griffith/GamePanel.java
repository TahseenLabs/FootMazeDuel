package griffith;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class GamePanel extends JPanel {
    private JLabel scoreLabel;
    private JButton resetButton;
    private Defender defender;
    private Striker striker;
    private Football football;
    private Timer footballTimer;
    private Goal goalTracker;
    private Timer movementTimer;

    private final Set<Integer> pressedKeys = new HashSet<>();
    private boolean footballMoving = false;
    private boolean awaitingNextRound = false;
    private boolean enterKeyLock = false;
    private String statusMessage = "";
    private boolean canStartNextRound = false;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(139, 69, 19));
        setLayout(new BorderLayout());

        striker = new Striker(-15, 280);
        defender = new Defender(500, 250);
        football = new Football(165, 280);
        goalTracker = new Goal();

        scoreLabel = new JLabel(goalTracker.getScoreText(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel, BorderLayout.NORTH);

        JPanel fieldPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Draw field
                g.setColor(Color.GREEN);
                g.fillRect(50, 50, getWidth() - 100, getHeight() - 100);

                g.setColor(Color.WHITE);
                g.drawRect(50, 50, getWidth() - 100, getHeight() - 100);
                g.drawLine(getWidth() / 2, 50, getWidth() / 2, getHeight() - 50);
                g.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
                g.fillOval(getWidth() / 2 - 5, getHeight() / 2 - 5, 10, 10);

                int sixYardDepth = 54;
                int sixYardWidth = 108;
                int eighteenYardDepth = 162;
                int eighteenYardWidth = 324;

                g.drawRect(50, getHeight() / 2 - eighteenYardWidth / 2, eighteenYardDepth, eighteenYardWidth);
                g.drawRect(50, getHeight() / 2 - sixYardWidth / 2, sixYardDepth, sixYardWidth);
                g.drawRect(getWidth() - 80 - eighteenYardDepth, getHeight() / 2 - eighteenYardWidth / 2, eighteenYardDepth, eighteenYardWidth);
                g.drawRect(getWidth() - 80 - sixYardDepth, getHeight() / 2 - sixYardWidth / 2, sixYardDepth, sixYardWidth);

                drawCornerArcsAndFlags(g);

                g.fillRect(50, getHeight() / 2 - 100, 30, 200); // Left goal
                g.fillRect(getWidth() - 80, getHeight() / 2 - 100, 30, 200); // Right goal

                defender.draw(g);
                striker.draw(g);
                football.draw(g, this);

                if (!statusMessage.isEmpty()) {
                    g.setFont(new Font("Arial", Font.BOLD, 40));
                    g.setColor(Color.YELLOW);
                    FontMetrics fm = g.getFontMetrics();
                    int msgWidth = fm.stringWidth(statusMessage);
                    int msgHeight = fm.getHeight();
                    g.drawString(statusMessage, (getWidth() - msgWidth) / 2, getHeight() / 2 + msgHeight / 2);
                }
            }

            private void drawCornerArcsAndFlags(Graphics g) {
                int flagSize = 15;
                Point[] corners = {
                        new Point(50, 50),
                        new Point(getWidth() - 50, 50),
                        new Point(50, getHeight() - 50),
                        new Point(getWidth() - 50, getHeight() - 50)
                };

                for (Point corner : corners) {
                    g.setColor(Color.WHITE);
                    int poleX = corner.x;
                    int poleY = corner.y;
                    if (corner.x == 50) {
                        poleX += 2;
                        g.drawLine(poleX, poleY, poleX, poleY - flagSize * 2);
                        g.setColor(Color.RED);
                        int[] xPoints = {poleX, poleX, poleX + flagSize};
                        int[] yPoints = {poleY - flagSize * 2, poleY - flagSize, poleY - flagSize * 2};
                        g.fillPolygon(xPoints, yPoints, 3);
                    } else {
                        poleX -= 2;
                        g.drawLine(poleX, poleY, poleX, poleY - flagSize * 2);
                        g.setColor(Color.RED);
                        int[] xPoints = {poleX, poleX, poleX - flagSize};
                        int[] yPoints = {poleY - flagSize * 2, poleY - flagSize, poleY - flagSize * 2};
                        g.fillPolygon(xPoints, yPoints, 3);
                    }
                }
            }
        };

        fieldPanel.setPreferredSize(new Dimension(800, 500));
        fieldPanel.setBackground(new Color(139, 69, 19));
        add(fieldPanel, BorderLayout.CENTER);

        footballTimer = new Timer(20, e -> {
            football.move();

            if (football.getBounds().intersects(defender.getBounds())) {
                footballTimer.stop();
                footballMoving = false;

                goalTracker.defenderSaved();
                statusMessage = "SAVED by the Defender!";
                Sound.playSound("sounds/defender_save.wav");

                endRound();
            }

            if (football.getX() >= getWidth() - 130) {
                footballTimer.stop();
                footballMoving = false;

                goalTracker.strikerScored();
                statusMessage = "GOAL!";
                Sound.playSound("sounds/cheer.wav");

                endRound();
            }

            repaint();
        });
        movementTimer = new Timer(20, e -> {
            if (!awaitingNextRound) {
                if (pressedKeys.contains(KeyEvent.VK_W)) striker.moveUp();
                if (pressedKeys.contains(KeyEvent.VK_S)) striker.moveDown();
                if (pressedKeys.contains(KeyEvent.VK_UP)) defender.moveUp();
                if (pressedKeys.contains(KeyEvent.VK_DOWN)) defender.moveDown();
                repaint();
            }
        });
        movementTimer.start();

        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                pressedKeys.add(code);

                if (code == KeyEvent.VK_ENTER && !enterKeyLock) {
                    enterKeyLock = true;
                    if (awaitingNextRound) {
                        statusMessage = "";
                        awaitingNextRound = false;
                        repaint();
                    } else if (!footballMoving) {
                        Timer launchDelay = new Timer(300, evt -> {
                            if (!footballMoving) {
                                football.setMoving(true);
                                footballTimer.start();
                                footballMoving = true;
                            }
                        });
                        launchDelay.setRepeats(false);
                        launchDelay.start();
                    }
                }

                if (code == KeyEvent.VK_SPACE && !awaitingNextRound) {
                    // Optionally add action for SPACE
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                pressedKeys.remove(code);
                if (code == KeyEvent.VK_ENTER) enterKeyLock = false;
            }
        });

        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);
    }

    private void resetGame() {
        striker.reset();
        defender.setY(250);
        football.resetPosition();
        football.setMoving(false);
        footballTimer.stop();
        footballMoving = false;
        goalTracker.reset();
        statusMessage = "";
        awaitingNextRound = false;
        scoreLabel.setText(goalTracker.getScoreText());
        requestFocusInWindow();
        repaint();
    }
    
    private void endRound() {
        football.resetPosition();
        football.setMoving(false);
        striker.setY(280);
        defender.setY(250);
        awaitingNextRound = true;
        scoreLabel.setText(goalTracker.getScoreText());
        repaint();
    }

}
