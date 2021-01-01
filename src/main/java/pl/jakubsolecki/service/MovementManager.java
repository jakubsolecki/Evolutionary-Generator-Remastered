package pl.jakubsolecki.service;

import pl.jakubsolecki.model.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class MovementManager {

    private final WorldBoard board;
    private final BoardEntityCollection entityCollection;
    private static final Random random = new Random();

    public MovementManager(WorldBoard board) {
        this.board = board;
        entityCollection = board.getEntityCollection();
    }

    public void changeAnimalsDirections() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> ait = entityCollection.animalIterator();

        while (ait.hasNext()) {
            Animal animal = (Animal) ait.next().getValue();

            if (random.nextInt(2)%2 == 0) {
                animal.rotateClockwise();
            }
            else {
                animal.rotateCounterClockwise();
            }
        }
    }

    public void moveAllAnimals() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> ait = entityCollection.animalIterator();

        while (ait.hasNext()) {
            Animal animal = (Animal) ait.next().getValue();
            Vector2D nextPos = animal.getNextPosition();

            if (!entityCollection.stoneAt(nextPos)) {
                animal.setPosition(nextPos);
            }
        }
    }
}
