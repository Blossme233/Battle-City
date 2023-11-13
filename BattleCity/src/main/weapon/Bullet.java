package main.weapon;

import main.MainFrame;
import main.base.Base;
import main.effect.Explosion;
import main.enumeration.Direction;
import main.enumeration.Group;
import main.obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet {
    private static final int SPEED = 30;
    public static BufferedImage leftBullet, rightBullet, upBullet, downBullet;
    // Load bullet pictures from dist when the fame starts
    static {
        try {
            leftBullet = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream("resources/images/bulletLeft.gif"));
            rightBullet = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream("resources/images/bulletRight.gif"));
            upBullet = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream("resources/images/bulletUp.gif"));
            downBullet = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream("resources/images/bulletDown.gif"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isAvailable = true;
    private int x, y;
    private Direction direction;
    private MainFrame mainFrame;

    private Group group;

    public Bullet(int x, int y, Direction direction, MainFrame mainFrame, Group group){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.mainFrame = mainFrame;
        this.group = group;
    }

    /**
     * Draw bullets
     * @param g
     */
    public void paint(Graphics g) {
        if (!isAvailable){
            mainFrame.bullets.remove(this);
        }
        this.move(g);
    }

    /**
     * Move tand towards to different directions
     * @param g
     */
    private void move(Graphics g){
        switch (direction){
            case UP -> {
                y -= SPEED;
                g.drawImage(upBullet, x, y, null);
                break;
            }
            case DOWN -> {
                y += SPEED;
                g.drawImage(downBullet, x, y, null);
                break;
            }
            case LEFT -> {
                x -= SPEED;
                g.drawImage(leftBullet, x, y, null);
                break;
            }
            case RIGHT -> {
                x += SPEED;
                g.drawImage(rightBullet, x, y, null);
                break;
            }
        }
    }

    /**
     * If a bullet hits a tank, then the tank dies
     * @param tank
     */
    public void collide(Tank tank) {
        if (this.group == tank.getGroup()){
            return;
        }
        Rectangle bulletRect = new Rectangle(this.x, this.y, 1, 1);
        Rectangle tankRect = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());
        if (bulletRect.intersects(tankRect)){
            this.die();
            tank.die();
            // Add explosion effects to main frame
            mainFrame.explosions.add(new Explosion(tank.getX(), tank.getY(), mainFrame, true));
        }
    }

    public void collide(Obstacle obstacle) {
        Rectangle bulletRect = new Rectangle(this.x, this.y, 1, 1);
        Rectangle obsRect = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
        if (bulletRect.intersects(obsRect)){
            this.die();
        }
    }

    public void collide(Base base) {
        Rectangle bulletRect = new Rectangle(this.x, this.y, 1, 1);
        Rectangle baseRect = new Rectangle(base.getX(), base.getY(), base.getWidth(), base.getHeight());
        if (bulletRect.intersects(baseRect)){
            this.die();
            mainFrame.bases.remove(base);
            // Add explosion effects to main frame
            mainFrame.explosions.add(new Explosion(base.getX(), base.getY(), mainFrame, true));
            mainFrame.gameOver();
        }
    }

    public void die() {
        isAvailable = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
