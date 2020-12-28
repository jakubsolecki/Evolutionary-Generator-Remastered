package pl.jakubsolecki.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.jakubsolecki.service.BoardEntityCollection;

@AllArgsConstructor
@Getter
public class WorldBoard {

    private final Vector2D TOP_RIGHT_CORNER;
    private final Vector2D TOP_LEFT_CORNER;
    private final Vector2D BOTTOM_RIGHT_CORNER;
    private final Vector2D BOTTOM_LEFT_CORNER;
    private final int WIDTH;
    private final int HEIGHT;
    // TODO: jungle corner coordinates?
    private final int JUNGLE_WIDTH;
    private final int JUNGLE_HEIGHT;

    private final BoardEntityCollection entityCollection;
}
