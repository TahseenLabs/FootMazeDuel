package griffith;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private JLabel scoreLabel; // Displays scores
    private JButton resetButton; // Button to reset the game
    private Defender defender; // Defender player
    private Striker striker; // Striker player
    private Football football; // Football object
    private Timer footballTimer; // Timer for ball movement
    private Goal goalTracker; // Tracks goals and saves

    private boolean footballMoving = false;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(139, 69, 19));
        setLayout(new BorderLayout());

        striker = new Striker(-15, 120);
        defender = new Defender(515, 120);
        football = new Football(165, 280);
        goalTracker = new Goal(); // Initialize Goal tracker

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

                g.fillRect(50, getHeight() / 2 - 100, 30, 200);
                g.fillRect(getWidth() - 80, getHeight() / 2 - 100, 30, 200);
                g.fillRect(50, getHeight() / 2 - 50, 30, 100);
                g.fillRect(getWidth() - 80, getHeight() / 2 - 50, 30, 100);

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
                    case KeyEvent.VK_W -> striker.move(); // Move striker up
                    case KeyEvent.VK_S -> striker.moveRight(); // Move striker down
                    case KeyEvent.VK_ENTER -> {
                        if (!footballMoving) {
                            football.setMoving(true);
                            footballTimer.start();
                            footballMoving = true;
                        }
                    }
                    case KeyEvent.VK_UP -> defender.moveUp(); // Move defender up
                    case KeyEvent.VK_DOWN -> defender.moveRight(); // Move defender down
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
        football.reset();
        football.moveWithStriker(ABORT);;
        footballTimer.stop();
        footballMoving = false;
        goalTracker.reset(); // Reset scores
        scoreLabel.setText(goalTracker.getScoreText()); // Update label
        requestFocusInWindow();
        repaint();
    }
}
