package main.effect;

import main.MainFrame;
import main.enumeration.Direction;
import main.weapon.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Draw pictures for explosion effect.
 */
public class Explosion {
    public static BufferedImage[] explosionList = new BufferedImage[16];
    static {
        try {
            for (int i = 1; i <= 16; i++) {
                explosionList[i-1] = ImageIO.read(Bullet.class.getClassLoader().getResourceAsStream(String.format("resources/images/e%d.gif", i)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean exploding = false;
    private int x, y;
    private MainFrame mainFrame;
    private int stage = 0;

    public Explosion(int x, int y, MainFrame mainFrame, boolean exploding){
        this.x = x;
        this.y = y;
        this.mainFrame = mainFrame;
        this.exploding = exploding;
        // Perform the explosion effect in another thread.
        new Thread(() -> new Audio("resources/audio/explode.wav").play()).start();
    }

    /**
     * To show the explosion pictures one by one
     * @param g
     */
    public void paint(Graphics g){
        g.drawImage(explosionList[stage++], x, y, null);
        if (stage >= explosionList.length){
            stage = 0;
            mainFrame.explosions.remove(this);
        }
    }

    public boolean isExploding() {
        return exploding;
    }
}
