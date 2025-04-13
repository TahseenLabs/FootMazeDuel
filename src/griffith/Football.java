package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import javax.swing.*;
import java.awt.*;

public class Football {
	 private int x, y;
	    private Image ballImage;

	    // Constructor initializes the football's position and loads the football image
	    public Football(int x, int y) {
	        ballImage = new ImageIcon("images/football-img.png").getImage(); 
	        this.x = x;
	        this.y = y;
	    }
	    
	    // Drawing football at specified position with a defined size (60x60)
	    public void draw(Graphics g, Component c) {
	        g.drawImage(ballImage, x, y, 60, 60, c); // I adjusted the football's size
	    }
}
