package pl.jakubsolecki.service;

import lombok.RequiredArgsConstructor;
import pl.jakubsolecki.containers.BoardEntityCollection;
import pl.jakubsolecki.model.*;

import java.util.*;

public class EntityManager {

    private final WorldBoard board;
    private final BoardEntityCollection entityCollection;
    private static final Random random = new Random();
    private final int reproduceEnergy;

    public EntityManager(WorldBoard board, int reproduceEnergy) {
        this.board = board;
        this.reproduceEnergy = reproduceEnergy;
        entityCollection = board.getEntityCollection();
    }

    public void allEat() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> aIt = entityCollection.animalIterator();
        while (aIt.hasNext()) {
            Animal animal = (Animal) aIt.next().getValue(); // FIXME possible favoring
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
    
    public void breed() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> aIt = board.getEntityCollection().animalIterator();

        while (aIt.hasNext()) {
            Animal animal = (Animal) aIt.next().getValue();
            List<IBoardEntity> entities = board.getEntityCollection().entitiesAt(animal.getPosition());

            if ((entities.size() == 3 && entities.contains(Grass.class)) ||
                (entities.size() == 2 && !entities.contains(Grass.class))) {
                Iterator<IBoardEntity> it = entities.iterator();
                ArrayList<Animal> animalPair = new ArrayList<>();
                int i = 0;
                IBoardEntity entity;

                while (it.hasNext()) {
                    entity = it.next();

                    if (entity instanceof Grass) {
                        continue;
                    }

                    animalPair.add(i++, (Animal) entity);
                }

                Animal animalParent1 = animalPair.get(0);
                Animal animalParent2 = animalPair.get(1);
                if (animalParent1.getEnergy() > reproduceEnergy && animalParent2.getEnergy() > reproduceEnergy) {
                    animalParent1.changeEnergy(-reproduceEnergy);
                    animalParent2.changeEnergy(-reproduceEnergy);
                    newAnimal(animalParent1.getPosition(), (animalParent1.getEnergy() + animalParent2.getEnergy())/2);
                }
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
        double targetArea = (jungleArea/regularGrassArea)*boardArea*2;

        do {
            int x = random.nextInt(board.getWIDTH());
            int y = random.nextInt(board.getHEIGHT());
            vect = new Vector2D(x, y);
            tries++;
        } while ((board.isOccupied(vect) || board.isInJungle(vect)) && tries < targetArea);

        int min = board.getGRASS_MIN_ENERGY();
        int max = board.getGRASS_MAX_ENERGY();
        int energy = random.nextInt(max - min) + min;

        Grass grass = new Grass(energy, vect);

        entityCollection.add(grass);
    }

    public void spawnJungle() {
        Vector2D vect;
        double tries = 0;
        double jungleArea = (board.getJUNGLE_HEIGHT()*board.getJUNGLE_WIDTH())*2;

        do {
            int x = random.nextInt(board.getJUNGLE_WIDTH()) + board.getJUNGLE_BOTTOM_LEFT().X;
            int y = random.nextInt(board.getJUNGLE_HEIGHT()) + board.getJUNGLE_BOTTOM_LEFT().Y;
            vect = new Vector2D(x, y);
            tries++;
        } while ((board.isOccupied(vect) || board.isInJungle(vect)) && tries < jungleArea);

        int min = board.getGRASS_MIN_ENERGY();
        int max = board.getGRASS_MAX_ENERGY();
        int energy = (random.nextInt(max - min) + min)*board.getJUNGLE_ENERGY_FACTOR();

        Grass grass = new Grass(energy, vect);

        entityCollection.add(grass);
    }

    public void spawnStone() {
        Vector2D vect;
        int tries = 0;
        int maxTries = (board.getHEIGHT()*board.getWIDTH()) / 4;
        do {
            int x = random.nextInt(board.getWIDTH());
            int y = random.nextInt(board.getHEIGHT());
            vect = new Vector2D(x, y);
        } while (board.isOccupied(vect) && tries < maxTries);

        Stone stone = new Stone(vect);

        entityCollection.add(stone);
    }
}
