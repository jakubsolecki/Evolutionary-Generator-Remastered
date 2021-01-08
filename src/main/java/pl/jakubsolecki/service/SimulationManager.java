package pl.jakubsolecki.service;

import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.jakubsolecki.model.WorldBoard;

@Singleton
@RequiredArgsConstructor
public class SimulationManager {

    @Setter
    private WorldBoard board;
    private final EntityManager entityManager;
    private final MovementManager movementManager;
    @Setter
    private boolean hasEnd;
    @Setter
    private int MAX_DAYS;
    private int presentDay = 0;


    public void start(int initAnimals) {
        // TODO
    }

    public void nextDay() {
        if (hasEnd && presentDay >= MAX_DAYS) {
            finish();
            return;
        }

        entityManager.removeDeadAnimals();
        movementManager.changeAnimalsDirections();
        movementManager.moveAllAnimals();
        entityManager.breed();
        entityManager.allEat();
        entityManager.spawnGrass();
        entityManager.spawnJungle();
        entityManager.allAge();
        presentDay++;

        nextDay();
    }

    public void finish() {
        // TODO
    }
}
