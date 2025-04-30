package griffith;

import java.awt.*;

import javax.swing.ImageIcon;

public class Defender {
    private int x, y;
    private final int WIDTH = 300;  // Match striker width
    private final int HEIGHT = 300; // Match striker height
    
    private final int HITBOX_WIDTH = 30;
    private final int HITBOX_HEIGHT = 100;
    
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
        if (y < 160) y += 5;
    }

    public void draw(Graphics g) {
        g.drawImage(defenderImage, x, y, WIDTH, HEIGHT, null); 
        
        //debug frame for hitbox
        /*g.setColor(Color.CYAN);
        Rectangle hb = getBounds();
        g.drawRect(hb.x, hb.y, hb.width, hb.height);*/
    }
    
    public Rectangle getBounds() {
        int offsetX = (WIDTH - HITBOX_WIDTH) / 2;
        int offsetY = HEIGHT - HITBOX_HEIGHT * 2;
        return new Rectangle(x + offsetX, y + offsetY, HITBOX_WIDTH, HITBOX_HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return HITBOX_HEIGHT;
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