package main.enumeration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * We support four directions for tank moving.
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    static List<Direction> directions = new ArrayList<>(){{
        add(UP);
        add(DOWN);
        add(LEFT);
        add(RIGHT);
    }};

    public static Direction random(){
        Collections.shuffle(directions);
        return directions.get(0);
    }
}
