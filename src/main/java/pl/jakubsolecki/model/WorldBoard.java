package pl.jakubsolecki.model;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class WorldBoard {

    private final Vector2D TOP_RIGHT_CORNER;
    private final Vector2D TOP_LEFT_CORNER;
    private final Vector2D BOTTOM_RIGHT_CORNER;
    private final Vector2D BOTTOM_LEFT_CORNER;
    private final int WIDTH;
    private final int HEIGHT;
    // TODO: jungle corner coordinates?
    private final int JUNGLE_WIDTH;
    private final int JUNGLE_HEIGHT;

    private final Multimap<Vector2D, Animal> animalMap = LinkedListMultimap.create();
    private final Map<Vector2D, Grass> grassMap = new HashMap<>();
    private final Map<Vector2D, Stone> stoneMap = new HashMap<>();

    public WorldBoard(int WIDTH, int HEIGHT, int JUNGLE_WIDTH, int JUNGLE_HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.JUNGLE_WIDTH = JUNGLE_WIDTH;
        this.JUNGLE_HEIGHT = JUNGLE_HEIGHT;
    }

//    public IBoardEntity objectAt(Vector2D coords) {
//
//    }

    // TODO: position availability checking here or in entity manager?
    public void addAnimal(Animal animal) {
        animalMap.put(animal.getPosition(), animal);
    }

    public void addGrass(Grass grass) {
        grassMap.put(grass.getPosition(), grass);
    }

    public void addStone(Stone stone) {
        stoneMap.put(stone.getPosition(), stone);
    }
}
