package pl.jakubsolecki.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BoardDirection {

    N, NE, E, SE, S, SW, W, NW;

    private final static List<BoardDirection> DIRECTIONS = List.of(N, NE, E, SE, S, SW, W, NW);
    private final static int SIZE = DIRECTIONS.size();
    private final static Random random = new Random();

    public static BoardDirection randomDirection() {
        return DIRECTIONS.get(random.nextInt(SIZE));
    }

    public BoardDirection nextDirection() {
        return DIRECTIONS.get((DIRECTIONS.indexOf(this)) + 1 % DIRECTIONS.size());
    }

    public BoardDirection previousDirection() {
        return DIRECTIONS.get((DIRECTIONS.indexOf(this)) - 1 % DIRECTIONS.size());
    }

    public Vector2D toUnitVector2D() {

        return switch (this) {
            case N -> new Vector2D(0, 1);
            case NE -> new Vector2D(1, 1);
            case E -> new Vector2D(1, 0);
            case SE -> new Vector2D(1, -1);
            case S -> new Vector2D(0, -1);
            case SW -> new Vector2D(-1, -1);
            case W -> new Vector2D(-1, 0);
            case NW -> new Vector2D(-1, 1);
        };
    }
}
