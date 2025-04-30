package griffith;

import javax.swing.*;
import java.awt.*;

public class Football {
    private int x, y;
    private final int WIDTH = 60;
    private final int HEIGHT = 60;
    private Image ballImage;
    private final int MOVE_SPEED = 5; // Horizontal speed
    private boolean isMoving = false;

    public Football(int x, int y) {
        ballImage = new ImageIcon("images/football-img.png").getImage(); 
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (isMoving) {
            x += MOVE_SPEED;
        }
    }

    public void draw(Graphics g, Component c) {
        g.drawImage(ballImage, x, y, 60, 60, c);
        
        //debug frame for hitbox
        /*Rectangle r = getBounds();
        g.setColor(Color.RED);
        g.drawRect(r.x, r.y, r.width, r.height);*/
    }

    public Rectangle getBounds() {
        int offsetX = 5;
        int offsetY = 5;
        return new Rectangle(x + offsetX, y + offsetY, WIDTH - 10, HEIGHT - 10);
    } 
    
    public void resetPosition() {
        x = 165;  // Reset to striker position
        y = 280;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

	public void setX(int i) {
		// TODO Auto-generated method stub
		
	}
}
