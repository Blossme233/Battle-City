package main.weapon;

import main.MainFrame;
import main.base.Base;
import main.enumeration.Direction;
import main.enumeration.Group;
import main.strategy.FireStrategy;
import main.strategy.SoftFireStrategy;
import main.utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public abstract class Tank {
    protected int speed;
    public BufferedImage leftTank, rightTank, upTank, downTank;
    private boolean isAvailable = true;
    protected int x, y;
    protected Direction direction;
    protected boolean moving = false;

    protected boolean randomMoving = false;

    protected MainFrame mainFrame;

    private Random random = new Random();

    protected boolean randomShoot;

    protected Group group;

    protected FireStrategy fireStrategy = new SoftFireStrategy();

    protected void initTankImages(Group group){
        try {
            String image = group == Group.FRIEND ? "resources/images/tank_friend.png" : "resources/images/tank_enemy.png";
            upTank = ImageIO.read(Tank.class.getClassLoader().getResourceAsStream(image));
            downTank = ImageUtil.rotateImage(upTank, 180);
            rightTank = ImageUtil.rotateImage(upTank, 90);
            leftTank = ImageUtil.rotateImage(upTank, -90);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Draw tanks
     * @param g
     */
    public void paint(Graphics g) {
        if (!isAvailable){
            mainFrame.tanks.remove(this);
        }
        if (randomShoot && random.nextInt(10) > 8){
            shoot();
        }
        if (randomMoving && random.nextInt(10) > 8){
            this.direction = Direction.random();
        }
        switch (direction){
            case UP -> {
                g.drawImage(upTank, x, y, null);
                break;
            }
            case DOWN -> {
                g.drawImage(downTank, x, y, null);
                break;
            }
            case LEFT -> {
                g.drawImage(leftTank, x, y, null);
                break;
            }
            case RIGHT -> {
                g.drawImage(rightTank, x, y, null);
                break;
            }
        }
        int preX = x;
        int preY = y;
        this.move(g);
        this.checkBound(preX, preY);
    }

    private void move(Graphics g){
        if (moving){
            switch (direction){
                case UP -> {
                    y -= speed;
                    g.drawImage(upTank, x, y, null);
                    break;
                }
                case DOWN -> {
                    y += speed;
                    g.drawImage(downTank, x, y, null);
                    break;
                }
                case LEFT -> {
                    x -= speed;
                    g.drawImage(leftTank, x, y, null);
                    break;
                }
                case RIGHT -> {
                    x += speed;
                    g.drawImage(rightTank, x, y, null);
                    break;
                }
            }
        }
    }

    /**
     * If tanks reached the boarders, they will stop and wait for changing direction.
     */
    private void checkBound(int preX, int preY){
        if (x < 1){
            x = 1;
        }
        if (x > mainFrame.getWidth() - this.getWidth()){
            x = mainFrame.getWidth() - this.getWidth();
        }
        if (y < 30){
            y = 30;
        }
        if (y > mainFrame.getHeight() - this.getHeight()){
            y = mainFrame.getHeight() - this.getHeight();
        }
        Rectangle tankRect = new Rectangle(x, y, this.getWidth(), this.getHeight());
        mainFrame.obstacles.forEach(obstacle -> {
            Rectangle obsRect = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
            if (obsRect.intersects(tankRect)){
                x = preX;
                y = preY;
            }
        });
        mainFrame.bases.forEach(base -> {
            Rectangle baseRect = new Rectangle(base.getX(), base.getY(), base.getWidth(), base.getHeight());
            if (tankRect.intersects(baseRect)){
                x = preX;
                y = preY;
            }
        });
    }

    public void shoot(){
        if (!isAvailable){
            return;
        }
        int size[] = this.getTankSize();
        int bulletX = x + size[0]/2;
        int bulletY = y + size[1]/2;
        fireStrategy.fire(bulletX, bulletY, direction, mainFrame, group);
    }

    private int[] getTankSize(){
        int size[] = new int[2];
        switch (direction){
            case UP -> {
                size[0] = upTank.getWidth();
                size[1] = upTank.getHeight();
                break;
            }
            case DOWN -> {
                size[0] = downTank.getWidth();
                size[1] = downTank.getHeight();
                break;
            }
            case LEFT -> {
                size[0] = leftTank.getWidth();
                size[1] = leftTank.getHeight();
                break;
            }
            case RIGHT -> {
                size[0] = rightTank.getWidth();
                size[1] = rightTank.getHeight();
                break;
            }
        }
        return size;
    }

    public int getWidth(){
        return this.getTankSize()[0];
    }

    public int getHeight(){
        return this.getTankSize()[1];
    }

    public void die() {
        isAvailable = false;
        if (this.group == Group.FRIEND){
            mainFrame.gameOver();
        }
        long remainEnemyTanks = mainFrame.tanks.stream().filter(tank -> tank.group == Group.ENEMY && tank.isAvailable).count();
        if (remainEnemyTanks == 0){
            mainFrame.gameOver();
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }
}
