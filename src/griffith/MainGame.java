package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGame {
    private static JFrame frame;
    private static GamePanel gamePanel;

    public static void main(String[] args) {
        frame = new JFrame("Football Maze Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800, 600);

        // Showing the Start Menu initially
        showStartMenu();

        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    private static void showStartMenu() {
        JPanel startMenuPanel = new JPanel();
        startMenuPanel.setLayout(new BorderLayout());
        startMenuPanel.setBackground(new Color(139, 69, 19));

        // Creating buttons
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removing Start Menu & showing the game
                frame.remove(startMenuPanel);
                gamePanel = new GamePanel();
                frame.add(gamePanel);
                frame.revalidate();
                frame.repaint();

                gamePanel.requestFocusInWindow();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitButton.setPreferredSize(new Dimension(200, 50));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Adding Buttons to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); 
        buttonPanel.setBackground(new Color(139, 69, 19));
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        startMenuPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(startMenuPanel);
    }
}
