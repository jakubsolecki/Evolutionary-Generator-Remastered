package pl.jakubsolecki.service;

import lombok.RequiredArgsConstructor;
import pl.jakubsolecki.model.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
public class EntityManager {

    private final WorldBoard board;
    private static final Random random = new Random();

    public void allEat() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> aIt = board.getEntityCollection().animalIterator();
        while (aIt.hasNext()) {
            Animal animal = (Animal) aIt.next().getValue();
            Optional<Grass> opt = board.getEntityCollection().grassAt(animal.getPosition());

            if (opt.isPresent()) {
               Grass grass = opt.get();
               animal.changeEnergy(grass.getENERGY());
               board.getEntityCollection().remove(grass);
            }
        }
    }

    public void allAge() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> aIt = board.getEntityCollection().animalIterator();
        while (aIt.hasNext()) {
            Animal animal = (Animal) aIt.next().getValue();
            animal.increaseAge();
        }
    }

    public boolean spawnAnimal(int startEnergy, Vector2D pos) {
        if (board.getEntityCollection().hasKey(pos)) {
            return false;
        }

        newAnimal(pos, startEnergy);
        return true;
    }

    public void spawnAnimal(int startEnergy) {
        Vector2D vect;
        do {
            int x = random.nextInt(board.getWIDTH());
            int y = random.nextInt(board.getHEIGHT());
            vect = new Vector2D(x, y);
        } while (board.getEntityCollection().hasKey(vect));

        newAnimal(vect, startEnergy);
    }

    private void newAnimal(Vector2D pos, int startEnergy) {
        BoardDirection direction = BoardDirection.randomDirection();
        Animal animal = new Animal(
                pos,
                startEnergy,
                direction
        );

        board.getEntityCollection().add(animal);
    }

    public void spawnGrass() {
        Vector2D vect;
        do {
            int x = random.nextInt(board.getWIDTH());
            int y = random.nextInt(board.getHEIGHT());
            vect = new Vector2D(x, y);
        } while (board.getEntityCollection().hasKey(vect));

        // TODO different energy for jungle grass

        Grass grass = new Grass(
                random.nextInt(
                        board.getGRASS_MAX_ENERGY() - board.getGRASS_MIN_ENERGY()
                ) + board.getGRASS_MIN_ENERGY(),
                vect
        );

        board.getEntityCollection().add(grass);
    }

    public void spawnStone() {
        // TODO
    }
}
