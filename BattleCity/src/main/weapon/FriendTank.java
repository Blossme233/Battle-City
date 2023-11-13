package main.weapon;

import main.MainFrame;
import main.enumeration.Direction;
import main.enumeration.Group;
import main.strategy.HardFireStrategy;

public class FriendTank extends Tank{
    private static final int SPEED = 10;
    public FriendTank(int x, int y, Direction direction, MainFrame mainFrame, Group group){
        this.initTankImages(group);
        this.speed = SPEED;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.mainFrame = mainFrame;
        this.group = group;
        this.fireStrategy = new HardFireStrategy();
    }
}
