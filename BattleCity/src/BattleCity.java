import main.MainFrame;

public class BattleCity {
    /**
     * The entrance of the game
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        MainFrame frame = new MainFrame();
        while (true){
            frame.repaint();
            Thread.sleep(100);
        }
    }
}
