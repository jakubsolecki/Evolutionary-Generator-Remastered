package pl.jakubsolecki.model;

import lombok.Getter;
import pl.jakubsolecki.containers.BoardEntityCollection;

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

    public WorldBoard(
            int WIDTH,
            int HEIGHT,
            int JUNGLE_WIDTH,
            int JUNGLE_HEIGHT,
            int GRASS_MIN_ENERGY,
            int GRASS_MAX_ENERGY,
            int JUNGLE_ENERGY_FACTOR) {

        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.JUNGLE_WIDTH = JUNGLE_WIDTH;
        this.JUNGLE_HEIGHT = JUNGLE_HEIGHT;
        this.GRASS_MIN_ENERGY = GRASS_MIN_ENERGY;
        this.GRASS_MAX_ENERGY = GRASS_MAX_ENERGY;
        this.JUNGLE_ENERGY_FACTOR = JUNGLE_ENERGY_FACTOR;
        entityCollection = new BoardEntityCollection();

        BOTTOM_LEFT = new Vector2D(0, 0);
        TOP_RIGHT = new Vector2D(WIDTH-1, HEIGHT-1);

        JUNGLE_BOTTOM_LEFT = new Vector2D(
                (WIDTH - JUNGLE_WIDTH)%2 - 1,
                (HEIGHT - JUNGLE_HEIGHT)%2 - 1
        );
        JUNGLE_TOP_RIGHT = new Vector2D(
                (WIDTH - JUNGLE_WIDTH)%2 + JUNGLE_WIDTH - 1,
                (HEIGHT - JUNGLE_HEIGHT)%2 + JUNGLE_HEIGHT - 1
        );

    }

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
