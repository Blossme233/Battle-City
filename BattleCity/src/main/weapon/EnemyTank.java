package main.weapon;

import main.MainFrame;
import main.enumeration.Direction;
import main.enumeration.Group;
import main.strategy.SoftFireStrategy;

public class EnemyTank extends Tank{
    private static final int SPEED = 5;
    public EnemyTank(int x, int y, Direction direction, MainFrame mainFrame, Group group, boolean randomMoving, boolean randomShoot){
        this.initTankImages(group);
        this.speed = SPEED;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.mainFrame = mainFrame;
        this.group = group;
        if (randomMoving){
            this.moving = true;
        }
        this.randomMoving = randomMoving;
        this.randomShoot = randomShoot;
        this.fireStrategy = new SoftFireStrategy();
    }
}
