package pl.jakubsolecki.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BoardDirection {

    N, NE, E, SE, S, SW, W, NW;

    private final static List<BoardDirection> directions = Arrays.asList(N, NE, E, SE, S, SW, W, NW);

    public static BoardDirection nextDirection(BoardDirection currentDirection) {
//        switch (prevDirection) {
//            case N:
//                return NE;
//                break;
//            case NE:
//                return E;
//                break;
//            case E:
//                return SE;
//                break;
//            case SE:
//                return S;
//                break;
//            case S:
//                return SW;
//                break;
//            case SW:
//                return W;
//                break;
//            case W:
//                return NW;
//                break;
//            case NW:
//                return N;
//                break;
//        }

        return directions.get((directions.indexOf(currentDirection)) + 1 % directions.size());
    }

    public static BoardDirection previousDirection(BoardDirection currentDirection) {
        return directions.get((directions.indexOf(currentDirection)) - 1 % directions.size());
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
