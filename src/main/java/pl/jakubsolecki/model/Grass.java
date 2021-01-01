package pl.jakubsolecki.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.jakubsolecki.model.interfaces.IBoardEntity;

@RequiredArgsConstructor
public class Grass implements IBoardEntity {

    @Getter
    private final int ENERGY;
    private final Vector2D POSITION;

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public Vector2D getPosition() {
        return POSITION;
    }
}
