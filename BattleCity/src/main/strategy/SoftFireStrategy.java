package main.strategy;

import main.MainFrame;
import main.enumeration.Direction;
import main.enumeration.Group;
import main.weapon.Bullet;

public class SoftFireStrategy implements FireStrategy{
    @Override
    public void fire(int bulletX, int bulletY, Direction direction, MainFrame mainFrame, Group group) {
        mainFrame.bullets.add(new Bullet(bulletX, bulletY, direction, mainFrame, group));
    }
}
