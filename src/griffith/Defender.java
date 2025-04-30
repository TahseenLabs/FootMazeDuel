package griffith;
import javax.swing.*;
import java.awt.*;

public class Defender {
    private int x, y;
    private final int WIDTH = 30;
    private final int HEIGHT = 80;
    private Image defenderImage;
    
    public Defender(int x, int y) {
        this.x = x;
        this.y = y;
        defenderImage = new ImageIcon("images/defender-img.png").getImage();  // Load defender image
    }

    public void moveUp() {
        if (y > 60) y -= 15;
    }

    public void moveDown() {
        if (y < 440) y += 15;
    }

    public void draw(Graphics g) {
        g.drawImage(defenderImage, x, y, WIDTH, HEIGHT, null);  // Draw defender image
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Method for defender to shoot the ball
    public void shootBall(Football football) {
        if (football.getX() > this.x && football.getX() < this.x + 30 && football.getY() > this.y && football.getY() < this.y + 80) {
            // Logic for defender shooting the ball, for example, moving it forward
            football.setX(football.getX() + 15);  // Move the ball a bit after defender "shoots"
            System.out.println("Defender shoots the ball!");
        }
    }

	public Object moveRight() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
