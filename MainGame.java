package griffith;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import javax.swing.*;

public class MainGame {
	
	public static void main(String[] args) {
        JFrame frame = new JFrame("Football Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Start Menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(new GamePanel(frame));
            frame.revalidate();
            frame.repaint();
        });
        
        JButton quitButton = new JButton("Quit Game");
        quitButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitButton.addActionListener(e -> System.exit(0));

        menuPanel.add(startButton);
        menuPanel.add(quitButton);

        frame.add(menuPanel);
        frame.setVisible(true);
    }
}