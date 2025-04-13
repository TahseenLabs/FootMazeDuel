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
    private Image ballImage; // Image representing the ball
    private int direction = 1; // Direction for up/down movement
    private final int MOVE_SPEED = 2; // Speed of movement
    private boolean isFollowingStriker = false; 

    // Constructor to initialize football's position and load image
    public Football(int x, int y) {
        ballImage = new ImageIcon("images/football-img.png").getImage(); 
        this.x = x;
        this.y = y;
    }

    public void move() {
            if (!isFollowingStriker) { 
                y += MOVE_SPEED * direction;
                if (y > 350) direction = -1; 
                if (y < 130) direction = 1;  
            } else {
                moveWithStriker(y); 
            }
        } 
    
    public void moveWithStriker(int strikerY) {
        if (isFollowingStriker) {
            this.x = 170;               // Near striker
            this.y = strikerY + 170;    // Foot-level of striker
        }
    }
    
    // Draws the ball on screen
    public void draw(Graphics g, Component c) {
        g.drawImage(ballImage, x, y, 60, 60, c);
    }

    // Resets ball to center and makes it follow the striker again
    public void reset() {
        this.x = 370; // Center
        this.y = 225;
        isFollowingStriker = true; 
    }
}
