package pl.jakubsolecki.service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.RequiredArgsConstructor;
import pl.jakubsolecki.model.*;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BoardEntityCollection {

    private final Multimap<Vector2D, Animal> animalMap = HashMultimap.create();
    private final Map<Vector2D, Grass> grassMap = new HashMap<>();
    private final Map<Vector2D, Stone> stoneMap = new HashMap<>();

    public EntityIterator createIterator() {
        return new EntityIterator();
    }

    private class EntityIterator {

        Iterator<Map.Entry<Vector2D, IBoardEntity>> it;
        // TODO: find better solution
        private final List<Collection> listOfMaps = Arrays.asList(
                animalMap.entries(), grassMap.entrySet(), stoneMap.entrySet()
        );
        private int nextCollection = 0;

        public EntityIterator() {
            listOfMaps.add(animalMap.entries());
            listOfMaps.add(grassMap.entrySet());
            listOfMaps.add(stoneMap.entrySet());
        }

        private void nexIterator() {
            Collection<?> collection = listOfMaps.get(nextCollection);
            if (collection == animalMap.entries()) {
                it = animalMap.entries()
                        .stream()
                        .map(e -> {
                            Map.Entry<Vector2D, IBoardEntity> entity = new Map.Entry<Vector2D, IBoardEntity>() {
                                private Vector2D key = e.getKey();
                                private IBoardEntity entity = e.getValue();

                                @Override
                                public Vector2D getKey() {
                                    return key;
                                }

                                @Override
                                public IBoardEntity getValue() {
                                    return entity;
                                }

                                @Override
                                public IBoardEntity setValue(IBoardEntity value) {
                                    entity = value;
                                    key = value.getPosition();
                                    return value;
                                }
                            };
                            return entity;
                        })
                        .collect(Collectors.toCollection(ArrayList::new))
                        .iterator();
            } else {
//                it = listOfMaps.get(nextCollection++).iterator();
                it = listOfMaps.get(nextCollection).iterator();
            }

        }

        public boolean isLast() {
            return nextCollection < 2;
        }

        public Optional<Map.Entry<Vector2D, IBoardEntity>> next() {

            if (!isLast() && !it.hasNext()) {
                nexIterator();
            } else if (isLast() && !it.hasNext()) {
                return Optional.empty();
            }

            return Optional.of(it.next());
        }
    }

    public boolean hasKey(Vector2D pos) {
        return animalMap.containsKey(pos) || grassMap.containsKey(pos) || stoneMap.containsKey(pos);
    }

    public List<IBoardEntity> entitiesAt(Vector2D pos) {

        List<IBoardEntity> entities = new ArrayList<>();

        if (animalMap.containsKey(pos)) {
            entities.addAll(animalMap.get(pos));
        }

        if (grassMap.containsKey(pos)) {
            entities.add(grassMap.get(pos));
        }

        if (stoneMap.containsKey(pos)) {
            entities.add(stoneMap.get(pos));
        }

        return entities;
    }

    public void add(IBoardEntity entity) {
        if (entity instanceof Animal) {
            animalMap.put(entity.getPosition(), (Animal) entity);
        }
        else if (entity instanceof Grass){
            grassMap.put(entity.getPosition(), (Grass) entity);
        }
        else if (entity instanceof Stone) {
            stoneMap.put(entity.getPosition(), (Stone) entity);
        }
    }

    public void remove(IBoardEntity entity) {
        if (entity instanceof Animal) {
            animalMap.remove(entity.getPosition(), entity);
        }
        else if (entity instanceof Grass){
            grassMap.remove(entity.getPosition());
        }
        else if (entity instanceof Stone) {
            stoneMap.remove(entity.getPosition());
        }
    }


}
