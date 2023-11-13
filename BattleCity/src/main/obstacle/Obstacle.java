package main.obstacle;

import main.MainFrame;

import java.awt.*;
import java.util.Random;

public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;
    private MainFrame mainFrame;

    public Obstacle(MainFrame mainFrame){
        Random random = new Random();
        this.x = random.nextInt(250, 800);
        this.y = random.nextInt(250, 600);
        this.width = random.nextInt(50, 100);
        this.height = random.nextInt(50, 100);
        this.mainFrame = mainFrame;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
