package pl.jakubsolecki.service;

import lombok.RequiredArgsConstructor;
import pl.jakubsolecki.model.WorldBoard;

@RequiredArgsConstructor
public class SimulationManager {

    private final WorldBoard board;
    private final EntityManager entityManager;
    private final MovementManager movementManager;
    private final boolean hasEnd;
    private final int MAX_DAYS;
    private int presentDay = 0;

    public void start(int initAnimals) {
        // TODO
    }

    public void nextDay() {
        if (hasEnd && presentDay >= MAX_DAYS)
            finish();

        entityManager.removeDeadAnimals();
        movementManager.changeAnimalsDirections();
        movementManager.moveAllAnimals();
        entityManager.breed();
        entityManager.allEat();
        entityManager.spawnGrass();
        entityManager.spawnJungle();
        entityManager.allAge();
        presentDay++;

    }

    public void finish() {
        // TODO
    }
}
