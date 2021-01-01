package pl.jakubsolecki.model;

import lombok.*;
import pl.jakubsolecki.model.interfaces.IBoardEntity;

@RequiredArgsConstructor
@Getter
public class Animal implements IBoardEntity {

    @NonNull
    @Setter
    private Vector2D position;
    @NonNull
    private int energy;
    private int age = 0;
    @NonNull private BoardDirection direction;

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getNextPosition() {
        return position.add(direction.toUnitVector2D());
    }

    public void rotateClockwise() {
        direction = direction.nextDirection();
    }

    public void rotateCounterClockwise() {
        direction = direction.previousDirection();
    }

    public void increaseAge() {
        age++;
    }

    public boolean isAlive() {
        return energy == 0;
    }

    public void changeEnergy(int deltaEnergy) {
        this.energy += deltaEnergy;
    }
}
