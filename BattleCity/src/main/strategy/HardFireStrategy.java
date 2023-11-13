package main.strategy;

import main.MainFrame;
import main.enumeration.Direction;
import main.enumeration.Group;
import main.weapon.Bullet;

public class HardFireStrategy implements FireStrategy{
    @Override
    public void fire(int bulletX, int bulletY, Direction direction, MainFrame mainFrame, Group group) {
        mainFrame.bullets.add(new Bullet(bulletX, bulletY, Direction.DOWN, mainFrame, group));
        mainFrame.bullets.add(new Bullet(bulletX, bulletY, Direction.UP, mainFrame, group));
        mainFrame.bullets.add(new Bullet(bulletX, bulletY, Direction.LEFT, mainFrame, group));
        mainFrame.bullets.add(new Bullet(bulletX, bulletY, Direction.RIGHT, mainFrame, group));
    }
}
