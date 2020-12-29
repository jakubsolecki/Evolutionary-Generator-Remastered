package pl.jakubsolecki.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BoardDirection {

    N, NE, E, SE, S, SW, W, NW;

    private final static List<BoardDirection> DIRECTIONS = Collections.unmodifiableList(Arrays.asList(N, NE, E, SE, S, SW, W, NW));
    private final static int SIZE = DIRECTIONS.size();
    private final static Random random = new Random();

    public static BoardDirection randomDirection() {
        return DIRECTIONS.get(random.nextInt(SIZE));
    }

    public static BoardDirection nextDirection(BoardDirection currentDirection) {
        return DIRECTIONS.get((DIRECTIONS.indexOf(currentDirection)) + 1 % DIRECTIONS.size());
    }

    public static BoardDirection previousDirection(BoardDirection currentDirection) {
        return DIRECTIONS.get((DIRECTIONS.indexOf(currentDirection)) - 1 % DIRECTIONS.size());
    }

    public static Vector2D toUnitVector2D(BoardDirection currentDirection) {

        Vector2D unitVector = null;
        switch (currentDirection) {
            case N:
                unitVector = new Vector2D(0, 1);
                break;
            case NE:
                unitVector = new Vector2D(1, 1);
                break;
            case E:
                unitVector = new Vector2D(1, 0);
                break;
            case SE:
                unitVector = new Vector2D(1, -1);
                break;
            case S:
                unitVector = new Vector2D(0, -1);
                break;
            case SW:
                unitVector = new Vector2D(-1, -1);
                break;
            case W:
                unitVector = new Vector2D(-1, 0);
                break;
            case NW:
                unitVector = new Vector2D(-1, 1);
                break;
        }

        return unitVector;
    }
}
