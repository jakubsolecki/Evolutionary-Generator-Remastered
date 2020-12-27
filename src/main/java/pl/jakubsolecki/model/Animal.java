package pl.jakubsolecki.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Animal implements IBoardEntity {

    private Vector2D position;
    private int energy;
    private int age;
    private BoardDirection direction;

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    // TODO: consider moving to movement manager
    public void changePosition() {
        position = position.add(BoardDirection.toUnitVector2D(direction));
    }

    public void rotateClockwise() {
        direction = BoardDirection.nextDirection(direction);
    }

    public void rotateCounterClockwise() {
        direction = BoardDirection.previousDirection(direction);
    }

    public void eat(int energy) {
        this.energy += energy;
    }

    public void increaseAge() {
        age++;
    }

    public boolean isAlive() {
        return energy == 0;
    }

    public void changeEnergy(int deltaEnergy) {
        this.energy += energy;
    }
}
