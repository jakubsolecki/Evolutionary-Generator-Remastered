package pl.jakubsolecki.service;

import pl.jakubsolecki.model.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class EntityManager {

    private final WorldBoard board;
    private final BoardEntityCollection entityCollection;
    private static final Random random = new Random();

    public EntityManager(WorldBoard board) {
        this.board = board;
        entityCollection = board.getEntityCollection();
    }

    public void allEat() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> aIt = entityCollection.animalIterator();
        while (aIt.hasNext()) {
            Animal animal = (Animal) aIt.next().getValue();
            Optional<Grass> opt = entityCollection.grassAt(animal.getPosition());

            if (opt.isPresent()) {
               Grass grass = opt.get();
               animal.changeEnergy(grass.getENERGY());
               entityCollection.remove(grass);
            }
        }
    }

    public void allAge() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> aIt = entityCollection.animalIterator();
        while (aIt.hasNext()) {
            Animal animal = (Animal) aIt.next().getValue();
            animal.increaseAge();
        }
    }

    public void removeDeadAnimals() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> aIt = entityCollection.animalIterator();
        while (aIt.hasNext()) {
            Animal animal = (Animal) aIt.next().getValue();
            if (!animal.isAlive()) {
                entityCollection.remove(animal);
            }
        }
    }

    public boolean spawnAnimal(int startEnergy, Vector2D pos) {
        if (board.isOccupied(pos)) {
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
        } while (board.isOccupied(vect));

        newAnimal(vect, startEnergy);
    }

    private void newAnimal(Vector2D pos, int startEnergy) {
        BoardDirection direction = BoardDirection.randomDirection();
        Animal animal = new Animal(
                pos,
                startEnergy,
                direction
        );

        entityCollection.add(animal);
    }

    public void spawnGrass() {
        Vector2D vect;
        double tries = 0;
        double boardArea = board.getHEIGHT()*board.getWIDTH();
        double jungleArea = board.getJUNGLE_HEIGHT()*board.getJUNGLE_WIDTH();
        double regularGrassArea = boardArea - jungleArea;
        double targetArea = (jungleArea/regularGrassArea)*boardArea;

        do {
            int x = random.nextInt(board.getWIDTH());
            int y = random.nextInt(board.getHEIGHT());
            vect = new Vector2D(x, y);
            tries++;
        } while ((board.isOccupied(vect) || board.isInJungle(vect)) && tries < 2*targetArea);

        int min = board.getGRASS_MIN_ENERGY();
        int max = board.getGRASS_MAX_ENERGY();
        int energy = random.nextInt(max - min) + min;

        Grass grass = new Grass(energy, vect);

        entityCollection.add(grass);
    }

    public void spawnJungle() {
        Vector2D vect;
        double tries = 0;
        double jungleArea = board.getJUNGLE_HEIGHT()*board.getJUNGLE_WIDTH();

        do {
            int x = random.nextInt(board.getJUNGLE_WIDTH()) + board.getJUNGLE_BOTTOM_LEFT().X;
            int y = random.nextInt(board.getJUNGLE_HEIGHT()) + board.getJUNGLE_BOTTOM_LEFT().Y;
            vect = new Vector2D(x, y);
            tries++;
        } while ((board.isOccupied(vect) || board.isInJungle(vect)) && tries < 2*jungleArea);

        int min = board.getGRASS_MIN_ENERGY();
        int max = board.getGRASS_MAX_ENERGY();
        int energy = (random.nextInt(max - min) + min)*board.getJUNGLE_ENERGY_FACTOR();

        Grass grass = new Grass(energy, vect);

        entityCollection.add(grass);
    }

    public void spawnStone() {
        Vector2D vect;
        do {
            int x = random.nextInt(board.getWIDTH());
            int y = random.nextInt(board.getHEIGHT());
            vect = new Vector2D(x, y);
        } while (board.isOccupied(vect));

        Stone stone = new Stone(vect);

        entityCollection.add(stone);
    }
}
