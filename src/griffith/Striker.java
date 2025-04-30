package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import javax.swing.*;
import java.awt.*;

class Striker {
    private int x, y;
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private Image strikerImage;
    
    // Constructor to set initial position of the Striker
    public Striker(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        strikerImage = new ImageIcon("images/striker-img.png").getImage(); // Adding Striker's image

    }
    
    // Method for vertical movement of the striker
    public void moveUp() {
    	if (y > 20) y -= 5;
    }
    
    // Method to move the striker down (moving right in the vertical sense)
    public void moveDown() {
    	if (y < 160) y += 5;
    }
    
    // Method to draw Striker's image
    public void draw(Graphics g) {
        g.drawImage(strikerImage, x, y, WIDTH, HEIGHT, null); // Drawing Striker's image

    }
    
    // Getters for x and y coordinates
    public int getX() { return x; }
    public int getY() { return y; }

	public void setY(int y) {
		this.y = 90;
	}
	  public void reset() {
	        this.y = 90;  // Resets to initial Y position
	    }
}