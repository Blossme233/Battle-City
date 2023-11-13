package main.strategy;

import main.MainFrame;
import main.enumeration.Direction;
import main.enumeration.Group;

public interface FireStrategy {
    void fire(int bulletX, int bulletY, Direction direction, MainFrame mainFrame, Group group);
}
