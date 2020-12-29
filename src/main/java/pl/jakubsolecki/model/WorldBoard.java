package pl.jakubsolecki.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.jakubsolecki.service.BoardEntityCollection;

@AllArgsConstructor
@Getter
public class WorldBoard {

    private final Vector2D TOP_RIGHT;
    private final Vector2D TOP_LEFT;
    private final Vector2D BOTTOM_RIGHT;
    private final Vector2D BOTTOM_LEFT;
    public final int WIDTH;
    public final int HEIGHT;
    // TODO: jungle corner coordinates?
    private final Vector2D JUNGLE_TOP_RIGHT;
    private final Vector2D JUNGLE_BOTTOM_LEFT;
    private final int JUNGLE_WIDTH;
    private final int JUNGLE_HEIGHT;

    // for double-sided slider
    private final int GRASS_MIN_ENERGY;
    private final int GRASS_MAX_ENERGY;
    private final int JUNGLE_FACTOR;

    private final BoardEntityCollection entityCollection;

    public void add(IBoardEntity entity) {
        // TODO ?
    }

    public boolean isInJungle(Vector2D pos) {
        // TODO
        return pos.isLowerLeft(JUNGLE_TOP_RIGHT) && pos.isUpperRight(JUNGLE_BOTTOM_LEFT);
    }
}
