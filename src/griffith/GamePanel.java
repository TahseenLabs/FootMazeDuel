package griffith;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private JLabel scoreLabel;
    private JButton resetButton;
    private Defender defender;
    private Striker striker;
    private Football football;
    private Timer footballTimer;
    private Goal goalTracker;

    private boolean footballMoving = false;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(139, 69, 19));
        setLayout(new BorderLayout());

        striker = new Striker(-15, 280);
        defender = new Defender(700, 250);
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

                g.setColor(Color.GREEN);
                g.fillRect(50, 50, getWidth() - 100, getHeight() - 100);

                g.setColor(Color.WHITE);
                g.drawRect(50, 50, getWidth() - 100, getHeight() - 100);
                g.drawLine(getWidth() / 2, 50, getWidth() / 2, getHeight() - 50);
                g.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
                g.fillOval(getWidth() / 2 - 5, getHeight() / 2 - 5, 10, 10);

                // Goals
                g.fillRect(50, getHeight() / 2 - 100, 30, 200);
                g.fillRect(getWidth() - 80, getHeight() / 2 - 100, 30, 200);

                defender.draw(g);
                striker.draw(g);
                football.draw(g, this);
            }
        };

        fieldPanel.setPreferredSize(new Dimension(800, 500));
        fieldPanel.setBackground(new Color(139, 69, 19));
        add(fieldPanel, BorderLayout.CENTER);

        footballTimer = new Timer(20, e -> {
            football.move();
            if (football.getX() + 20 >= defender.getX()) {
                footballTimer.stop();
                footballMoving = false;

                if (football.getY() >= defender.getY() && football.getY() <= defender.getY() + defender.getHeight()) {
                    goalTracker.defenderSaved();
                    JOptionPane.showMessageDialog(this, "SAVED by the Defender!");
                } else {
                    goalTracker.strikerScored();
                    JOptionPane.showMessageDialog(this, "GOAL!");
                }

                scoreLabel.setText(goalTracker.getScoreText());
                football.resetPosition();
                football.setMoving(false);
            }
            repaint();
        });

        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> striker.move(); // up
                    case KeyEvent.VK_S -> striker.moveRight(); // down
                    case KeyEvent.VK_UP -> defender.move(); // up
                    case KeyEvent.VK_DOWN -> defender.moveRight(); // down
                    case KeyEvent.VK_ENTER -> {
                        if (!footballMoving) {
                            football.setMoving(true);
                            footballTimer.start();
                            footballMoving = true;
                        }
                    }
                }
                repaint();
            }
        });

        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);
    }

    private void resetGame() {
        striker.setY(280);
        defender.setY(250);
        football.resetPosition();
        football.setMoving(false);
        footballTimer.stop();
        footballMoving = false;
        goalTracker.reset();
        scoreLabel.setText(goalTracker.getScoreText());
        requestFocusInWindow();
        repaint();
    }
}
