package griffith;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private JLabel scoreLabel;
    private JButton resetButton;
    private JPanel fieldPanel;
    private JFrame frame;

    public GamePanel(JFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(139, 69, 19)); // Brown background
        setLayout(new BorderLayout());

        // Scoreboard at the top
        scoreLabel = new JLabel("Striker: 0 | Defender: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel, BorderLayout.NORTH);

        // Game field panel (Initially hidden)
        fieldPanel = new JPanel() {
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
            }
        };
        fieldPanel.setPreferredSize(new Dimension(800, 500));
        fieldPanel.setBackground(new Color(139, 69, 19));
        add(fieldPanel, BorderLayout.CENTER);
        fieldPanel.setVisible(true);

        // Adding a Reset Button
        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);
    }

    private void resetGame() {
        scoreLabel.setText("Striker: 0 | Defender: 0");
        repaint();
    }
}