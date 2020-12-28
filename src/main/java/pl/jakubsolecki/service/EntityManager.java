package pl.jakubsolecki.service;

import lombok.RequiredArgsConstructor;
import pl.jakubsolecki.model.IBoardEntity;
import pl.jakubsolecki.model.Vector2D;
import pl.jakubsolecki.model.WorldBoard;

import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
public class EntityManager {

    private final WorldBoard board;

    public void allEat() {
        Iterator<Map.Entry<Vector2D, IBoardEntity>> it = board.getEntityCollection().animalIterator();
    }
}
