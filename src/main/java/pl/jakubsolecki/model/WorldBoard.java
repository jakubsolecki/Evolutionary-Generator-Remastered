package pl.jakubsolecki.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.jakubsolecki.containers.BoardEntityCollection;

@AllArgsConstructor
@Getter
public class WorldBoard {

    private final Vector2D TOP_RIGHT;
    private final Vector2D BOTTOM_LEFT;
    private final int WIDTH;
    private final int HEIGHT;

    private final Vector2D JUNGLE_TOP_RIGHT;
    private final Vector2D JUNGLE_BOTTOM_LEFT;
    private final int JUNGLE_WIDTH;
    private final int JUNGLE_HEIGHT;

    private final int GRASS_MIN_ENERGY;
    private final int GRASS_MAX_ENERGY;
    private final int JUNGLE_ENERGY_FACTOR;

    private final BoardEntityCollection entityCollection; // TODO hide (do not expose via getter)

    public boolean isInJungle(Vector2D pos) {
        return pos.isLowerLeft(JUNGLE_TOP_RIGHT) && pos.isUpperRight(JUNGLE_BOTTOM_LEFT);
    }

    public boolean isOnBoard(Vector2D pos) {
        return pos.isLowerLeft(TOP_RIGHT) && pos.isUpperRight(BOTTOM_LEFT);
    }

    public Vector2D covertToBoardPosition(Vector2D oldPos) {
        return oldPos.translateToBoard(WIDTH, HEIGHT);
    }

    public boolean isOccupied(Vector2D pos) {
        return entityCollection.hasKey(pos);
    }

    public boolean availableToPlace(Vector2D pos) {
        return entityCollection.isStoneAt(pos);
    }

}
