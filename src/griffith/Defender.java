package griffith;

import java.awt.*;

import javax.swing.ImageIcon;

public class Defender {
    private int x, y;
    private final int WIDTH = 300;  // Match striker width
    private final int HEIGHT = 300; // Match striker height
    private Image defenderImage;

    public Defender(int x, int y) {
        this.x = x;
        this.y = y;
        defenderImage = new ImageIcon("images/defender-img.png").getImage(); // Use defender image
    }

    public void moveUp() {
        if (y > 20) y -= 5;
    }

    public void moveDown() {
        if (y < 440) y += 5;
    }

    public void draw(Graphics g) {
        g.drawImage(defenderImage, x, y, WIDTH, HEIGHT, null); 
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
        this.y = y;}
     // Defender shooting the ball logic
        public void shootBall(Football football) {
            if (football.getX() > this.x && football.getX() < this.x + 30 &&
                football.getY() > this.y && football.getY() < this.y + 80) {
                football.setX(football.getX() + 15);
                System.out.println("Defender shoots the ball!");}
    }

	public Object moveRight() {
		return null;
	
	
	}
}