package main.utils;
import main.enumeration.Direction;
import main.strategy.FireStrategy;
import main.weapon.Tank;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Listen key pressed event.
 * If arrow buttons were pressed, then move tanks
 * If the space button was pressed, then shoot.
 */
public class BattleCityKeyListener extends KeyAdapter {
    boolean moveUp = false;
    boolean moveDown = false;
    boolean moveLeft = false;
    boolean moveRight = false;

    private Tank myTank;

    public BattleCityKeyListener(Tank myTank){
        super();
        this.myTank = myTank;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP -> {
                moveUp = true;
                break;
            }
            case KeyEvent.VK_DOWN -> {
                moveDown = true;
                break;
            }
            case KeyEvent.VK_LEFT -> {
                moveLeft = true;
                break;
            }
            case KeyEvent.VK_RIGHT -> {
                moveRight = true;
                break;
            }
            case KeyEvent.VK_SPACE -> {
                myTank.shoot();
                break;
            }
        }
        setDirection();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP -> {
                moveUp = false;
                break;
            }
            case KeyEvent.VK_DOWN -> {
                moveDown = false;
                break;
            }
            case KeyEvent.VK_LEFT -> {
                moveLeft = false;
                break;
            }
            case KeyEvent.VK_RIGHT -> {
                moveRight = false;
                break;
            }
        }
        setDirection();
    }

    private void setDirection(){
        myTank.setMoving(moveUp || moveDown || moveLeft || moveRight);
        if (moveUp){
            myTank.setDirection(Direction.UP);
        }else if (moveDown){
            myTank.setDirection(Direction.DOWN);
        }else if(moveLeft){
            myTank.setDirection(Direction.LEFT);
        }else if (moveRight){
            myTank.setDirection(Direction.RIGHT);
        }
    }
}