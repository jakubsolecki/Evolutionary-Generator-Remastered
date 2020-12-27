package pl.jakubsolecki.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Animal implements IBoardEntity {

    private final Genotype GENOTYPE; // TODO: change constructor
    private Vector2D position;
    private int energy;
    private int age;
    //private BoardDirection direction;


    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }


}
