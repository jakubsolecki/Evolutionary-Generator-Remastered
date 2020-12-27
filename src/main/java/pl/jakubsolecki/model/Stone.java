package pl.jakubsolecki.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Stone implements IBoardEntity {

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
