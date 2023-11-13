package main.decorator;

import main.MainFrame;
import main.base.Base;

import java.awt.*;

public class Shape extends BaseDecorator{
    Base base;
    public Shape(Base base){
        this.base = base;
    }
    @Override
    public void paint(Graphics g) {
        this.base.paint(g);
        this.width = 20;
        this.height = 40;
        this.x = (MainFrame.FRAME_WIDTH - this.width)/2;
        this.y = MainFrame.FRAME_HEIGHT - this.height;
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval(x, y, width, height);
        g.setColor(c);
    }
}
