package main.decorator;

import main.MainFrame;
import main.base.Base;

import java.awt.*;

public class Border extends BaseDecorator{
    Base base;
    public Border(Base base){
        this.base = base;
    }
    @Override
    public void paint(Graphics g) {
        this.width = 60;
        this.height = 60;
        this.x = (MainFrame.FRAME_WIDTH - this.width)/2 - 5;
        this.y = MainFrame.FRAME_HEIGHT - this.height - 5;
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width+10, height+10);
        g.setColor(c);
        this.base.paint(g);
    }
}
