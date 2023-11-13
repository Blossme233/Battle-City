package main;

import main.base.Base;
import main.base.EnemyBase;
import main.base.FriendBase;
import main.decorator.Border;
import main.decorator.Shape;
import main.enumeration.Direction;
import main.enumeration.Group;
import main.obstacle.Obstacle;
import main.strategy.HardFireStrategy;
import main.utils.BattleCityKeyListener;
import main.weapon.Bullet;
import main.effect.Explosion;
import main.weapon.Tank;
import main.weapon.TankFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The game window, play everything here.
 */
public class MainFrame extends Frame {
    public static final int FRAME_WIDTH = 1200;
    public static final int FRAME_HEIGHT = 800;

    BufferedImage gameOver = null;
    {
        try {
            gameOver = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream("resources/images/game_over.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isGaveOver = false;
    Tank myTank = TankFactory.getInstance().getTank(Group.FRIEND, this);
    public List<Bullet> bullets = new ArrayList<>();

    public List<Explosion> explosions = new ArrayList<>();

    public List<Obstacle> obstacles = new ArrayList<>(){{
        add(new Obstacle(MainFrame.this));
        add(new Obstacle(MainFrame.this));
        add(new Obstacle(MainFrame.this));
        add(new Obstacle(MainFrame.this));
        add(new Obstacle(MainFrame.this));
    }};

    public List<Base> bases = new ArrayList<>(){{
        add(new Shape(new Border(new FriendBase())));
        add(new EnemyBase());
    }};

    public List<Tank> tanks = new ArrayList<>(){{
        add(myTank);
        add(TankFactory.getInstance().getTank(Group.ENEMY, MainFrame.this));
        add(TankFactory.getInstance().getTank(Group.ENEMY, MainFrame.this));
        add(TankFactory.getInstance().getTank(Group.ENEMY, MainFrame.this));
        add(TankFactory.getInstance().getTank(Group.ENEMY, MainFrame.this));
        add(TankFactory.getInstance().getTank(Group.ENEMY, MainFrame.this));
    }};
    public MainFrame(){
        this.setBackground(Color.BLACK);
        this.setTitle("Battle City");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(Boolean.FALSE);
        this.setVisible(Boolean.TRUE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new BattleCityKeyListener(myTank));
    }

    @Override
    public void paint(Graphics g) {
        // draw bullets
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            int bulletX = bullet.getX();
            int bulletY = bullet.getY();
            if (bulletX < 0 || bulletX > FRAME_WIDTH || bulletY < 0 || bulletY > FRAME_HEIGHT){
                bullets.remove(i);
                continue;
            }
            bullet.paint(g);
        }

        //draw tanks
        for (int i = 0; i < tanks.size(); i++) {
            Tank tank = tanks.get(i);
            int tankX = tank.getX();
            int tankY = tank.getY();
            if (tankX < 0 || tankX > FRAME_WIDTH || tankY < 0 || tankY > FRAME_HEIGHT){
                tanks.remove(i);
                continue;
            }
            tanks.get(i).paint(g);
        }

        //draw obstacles
        obstacles.forEach(obstacle -> obstacle.paint(g));

        //draw bases
        bases.forEach(base -> base.paint(g));

        this.detectCollision();
        for (int i = 0; i < explosions.size(); i++) {
            Explosion explosion = explosions.get(i);
            if (!explosion.isExploding()){
                explosions.remove(explosion);
                continue;
            }
            explosion.paint(g);
        }
        if (isGaveOver){
            g.drawImage(gameOver, (FRAME_WIDTH - gameOver.getWidth())/2, 100, null);
        }
    }

    /**
     * detect collision between tanks and bullets
     */
    private void detectCollision(){
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collide(tanks.get(j));
            }
            for (int j = 0; j < obstacles.size(); j++) {
                bullets.get(i).collide(obstacles.get(j));
            }
            for (int k = 0; k < bases.size(); k++) {
                bullets.get(i).collide(bases.get(k));
            }
        }
    }

    public void gameOver() {
        isGaveOver = true;
    }
}
