package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import javax.swing.*;

public class MainGame {
	
	public static void main(String[] args) {
        JFrame frame = new JFrame("Football Maze Game");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
     // Adding Game Panel
    	GamePanel panel = new GamePanel();
    	frame.add(panel);
    	frame.pack();

	}
}
