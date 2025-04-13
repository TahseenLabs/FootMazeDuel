package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import javax.swing.*;
import java.awt.*;

class Defender {
    private int x, y;
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private final int MOVE_SPEED = 5;
    private int direction = 1; 
    private Image defenderImage;
    
    // Constructor to set the initial position of the defender
    public Defender(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        defenderImage = new ImageIcon("images/defender-img.png").getImage(); // Adding Defender's image

    }
    
    // Method for vertical movement of the defender
    public void move() {
        y += MOVE_SPEED * direction;
        if (y > 200) direction = -1;
        if (y < 40) direction = 1;
        
    }
    
    // Method to move the defender down (moving right in the vertical sense)
    public void moveRight() {
        if (y < 350) {
            y += MOVE_SPEED;
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(defenderImage, x, y, WIDTH, HEIGHT, null); // Drawing Defender's image
    }
    
    // Getters for x and y coordinates
    public int getX() { return x; }
    public int getY() { return y; }
}