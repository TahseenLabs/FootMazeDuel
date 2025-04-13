package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private JLabel scoreLabel;
    private JButton resetButton;
    private Defender defender;
    private Striker striker;
    private Football football;
    
    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(139, 69, 19)); // Brown background
        setLayout(new BorderLayout());
        
        striker = new Striker(-15, 120); // Striker positioned near the left side of the football
        defender = new Defender(515, 120); // Defender positioned near the right side of the football
        football = new Football(370, 225); // Centered football near the middle
        
        // Scoreboard at the top
        scoreLabel = new JLabel("Striker: 0 | Defender: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel, BorderLayout.NORTH);
        
        // Game field
        JPanel fieldPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Drawing football field (green)
                g.setColor(Color.GREEN);
                g.fillRect(50, 50, getWidth() - 100, getHeight() - 100);
                
                // Drawing boundary lines 
                g.setColor(Color.WHITE); 
                g.drawRect(50, 50, getWidth() - 100, getHeight() - 100);
                
                // Center line and circle
                g.drawLine(getWidth() / 2, 50, getWidth() / 2, getHeight() - 50);
                g.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
                g.fillOval(getWidth() / 2 - 5, getHeight() / 2 - 5, 10, 10);
                
                // Goal areas (left and right)
                g.fillRect(50, getHeight() / 2 - 100, 30, 200);              // Left goal
                g.fillRect(getWidth() - 80, getHeight() / 2 - 100, 30, 200); // Right goal
                g.fillRect(50, getHeight() / 2 - 50, 30, 100);               // Left post
                g.fillRect(getWidth() - 80, getHeight() / 2 - 50, 30, 100);  // Right post
                
                // Drawing Game Objects
                defender.draw(g); // Drawing defender
                striker.draw(g); // Drawing striker
                football.draw(g, this); // Drawing football
            }
        };
        fieldPanel.setPreferredSize(new Dimension(800, 500));
        fieldPanel.setBackground(new Color(139, 69, 19));
        add(fieldPanel, BorderLayout.CENTER);
        
        // Adding Key Listener for movement
        setFocusable(true);
        requestFocusInWindow(); 
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> striker.move();        // Up Arrow
                case KeyEvent.VK_DOWN -> striker.moveRight(); // Down Arrow
                case KeyEvent.VK_W -> defender.move();        // W
                case KeyEvent.VK_S -> defender.moveRight();   // S
                }
                repaint(); 
            }
        });
        
        // Adding a Reset Button
        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        add(resetButton, BorderLayout.SOUTH);
    }
}
