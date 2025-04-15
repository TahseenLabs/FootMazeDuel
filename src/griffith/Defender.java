package griffith;

import java.awt.*;

public class Defender {
    private int x, y;

    public Defender(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveUp() {
        if (y > 60) y -= 15;
    }

    public void moveDown() {
        if (y < 440) y += 15;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 30, 80);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return 80;
    }

    public void setY(int y) {
        this.y = y;
    }

	public Object moveRight() {
		// TODO Auto-generated method stub
		return null;
	
	
	}
}
