package pl.jakubsolecki.model.interfaces;

import pl.jakubsolecki.model.Vector2D;

public interface IBoardEntity {

    public boolean canMove();
    public Vector2D getPosition();
}
