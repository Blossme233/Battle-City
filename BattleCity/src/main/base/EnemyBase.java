package main.base;

import main.MainFrame;

import java.awt.*;

public class EnemyBase extends Base{
    @Override
    public void paint(Graphics g) {
        this.width = 50;
        this.height = 50;
        this.x = (MainFrame.FRAME_WIDTH - this.width)/2;
        this.y = 20;
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }
}
