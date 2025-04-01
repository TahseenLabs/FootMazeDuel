package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
	
	public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(139, 69, 19)); // Brown background
    }
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Drawing football field (green)
	    g.setColor(Color.GREEN);
	    g.fillRect(50, 50, getWidth() - 100, getHeight() - 100);
	}
}