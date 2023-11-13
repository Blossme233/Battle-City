package main.base;

import main.MainFrame;

import java.awt.*;

public class FriendBase extends Base{
    @Override
    public void paint(Graphics g) {
        this.width = 50;
        this.height = 50;
        this.x = (MainFrame.FRAME_WIDTH - this.width)/2;
        this.y = MainFrame.FRAME_HEIGHT - this.height;
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }
}
