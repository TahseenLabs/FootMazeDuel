package griffith;
/*
 * Group Member-1: Olimeh kelvin     ; Student No. 3112489
 * Group Member-2: Tahseen Ahmad     ; Student No. 3135743
 * Group Member-3: Francis Ngonadi   ; Student No. 3138335
 * Group Member-4: Komarovskyi Denys ; Student No. 3105289
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Striker {
    private int x, y;
    private final int WIDTH = 12;
    private final int HEIGHT = 50;
    private final int MOVE_SPEED = 5;
    private int direction = 1; // 1 for up, -1 for down;
    
    public Striker(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }
    
    public void move() {
        y += MOVE_SPEED * direction;
        if (y > 350) direction = -1;
        if (y < 150) direction = 1;
        
    }
    
    public void moveRight() {
        if (y < 350) {
            y += MOVE_SPEED;
        }
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
}