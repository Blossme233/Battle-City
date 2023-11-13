package main.base;

import main.MainFrame;
import main.enumeration.Group;

import java.awt.*;

public abstract class Base {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public abstract void paint(Graphics g);

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
