package main.weapon;

import main.MainFrame;
import main.enumeration.Direction;
import main.enumeration.Group;

import java.util.Random;

public class TankFactory {

    public static TankFactory tankFactory;
    private static Random random = new Random();

    private TankFactory(){}

    public static TankFactory getInstance(){
        if (tankFactory == null){
            tankFactory = new TankFactory();
        }
        return tankFactory;
    }

    public Tank getTank(Group group, MainFrame frame){
        if (group == Group.FRIEND){
            return new FriendTank(100, 100, Direction.DOWN, frame, group);
        }else if (group == Group.ENEMY){
            return new EnemyTank(random.nextInt(0, frame.FRAME_WIDTH), random.nextInt(200, frame.FRAME_HEIGHT/3), Direction.DOWN, frame, group, true, true);
        }
        return null;
    }
}
