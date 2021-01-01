import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jakubsolecki.model.*;
import pl.jakubsolecki.containers.BoardEntityCollection;
import pl.jakubsolecki.model.interfaces.IBoardEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class BoardEntityCollectionTest {

    private BoardEntityCollection entityCollection;

    @BeforeEach
    public void setup() {
        entityCollection  = new BoardEntityCollection();
    }

    @Test
    public void addAnimalTest() {
        // given
        var pos = new Vector2D(1, 1);
        var animal = Mockito.mock(Animal.class);
        when(animal.getPosition()).thenReturn(pos);

        // when
        entityCollection.add(animal);

        // then
        assertTrue(entityCollection.hasKey(pos)); // should not use own method to test other
        assertEquals(animal, entityCollection.entitiesAt(pos).get(0)); // should not use own method to test other
    }

    @Test
    public void addGrassTest() {
        // given
        var pos = new Vector2D(1, 1);
        var grass = Mockito.mock(Grass.class);
        when(grass.getPosition()).thenReturn(pos);

        // when
        entityCollection.add(grass);

        // then
        assertTrue(entityCollection.hasKey(pos));
        assertEquals(grass, entityCollection.entitiesAt(pos).get(0));
    }

    @Test
    public void addStoneTest() {
        // given
        var pos = new Vector2D(1, 1);
        var stone = Mockito.mock(Stone.class);
        when(stone.getPosition()).thenReturn(pos);

        // when
        entityCollection.add(stone);

        // then
        assertTrue(entityCollection.hasKey(pos));
        assertEquals(stone, entityCollection.entitiesAt(pos).get(0));
    }

    @Test
    public void removeAnimalTest() {
        // given
        var pos = new Vector2D(1, 1);
        var animal = Mockito.mock(Animal.class);
        when(animal.getPosition()).thenReturn(pos);

        // when
        entityCollection.add(animal);
        entityCollection.remove(animal);

        // then
        assertFalse(entityCollection.hasKey(pos));
        assertTrue(entityCollection.entitiesAt(pos).isEmpty());
    }

    @Test
    public void removeGrassTest() {
        // given
        var pos = new Vector2D(1, 1);
        var grass = Mockito.mock(Grass.class);
        when(grass.getPosition()).thenReturn(pos);

        // when
        entityCollection.add(grass);
        entityCollection.remove(grass);

        // then
        assertFalse(entityCollection.hasKey(pos));
        assertTrue(entityCollection.entitiesAt(pos).isEmpty());
    }

    @Test
    public void removeStoneTest() {
        // given
        var pos = new Vector2D(1, 1);
        var stone = Mockito.mock(Stone.class);
        when(stone.getPosition()).thenReturn(pos);

        // when
        entityCollection.add(stone);
        entityCollection.remove(stone);

        // then
        assertFalse(entityCollection.hasKey(pos));
        assertTrue(entityCollection.entitiesAt(pos).isEmpty());
    }

    @Test
    public void iteratorTest() {
        // given
        List<IBoardEntity> resList = new LinkedList<>();

        List<Animal> animals = Arrays.asList(
                Mockito.mock(Animal.class),
                Mockito.mock(Animal.class),
                Mockito.mock(Animal.class)
        );

        var grass = Mockito.mock(Grass.class);
        when(grass.getPosition()).thenReturn(new Vector2D(5, 5));

        var stone = Mockito.mock(Stone.class);
        when(stone.getPosition()).thenReturn(new Vector2D(8, 8));

        // when
        AtomicInteger i = new AtomicInteger();
        animals.forEach(a -> {
            when(a.getPosition()).thenReturn(new Vector2D(i.get(), i.get()));
            i.getAndIncrement();

            entityCollection.add(a);
        });
        entityCollection.add(grass);
        entityCollection.add(stone);

        Iterator<Map.Entry<Vector2D, IBoardEntity>> it = entityCollection.iterator();

        // then
        while (it.hasNext()) {
            IBoardEntity res = it.next().getValue();
            System.out.println(res); // just for sure
            resList.add(res);
        }

        assertTrue(resList.containsAll(animals));
        assertTrue(resList.contains(grass));
        assertTrue(resList.contains(stone));
        assertEquals(5, resList.size());
    }

    @Test
    public void animalIteratorTest() {
        List<IBoardEntity> resList = new LinkedList<>();

        List<Animal> animals = Arrays.asList(
                Mockito.mock(Animal.class),
                Mockito.mock(Animal.class),
                Mockito.mock(Animal.class)
        );

        var grass = Mockito.mock(Grass.class);
        when(grass.getPosition()).thenReturn(new Vector2D(5, 5));

        var stone = Mockito.mock(Stone.class);
        when(stone.getPosition()).thenReturn(new Vector2D(8, 8));

        // when
        AtomicInteger i = new AtomicInteger();
        animals.forEach(a -> {
            when(a.getPosition()).thenReturn(new Vector2D(i.get(), i.get()));
            i.getAndIncrement();

            entityCollection.add(a);
        });
        entityCollection.add(grass);
        entityCollection.add(stone);

        Iterator<Map.Entry<Vector2D, IBoardEntity>> it = entityCollection.animalIterator();

        // then
        while (it.hasNext()) {
            IBoardEntity res = it.next().getValue();
            System.out.println(res); // just for sure
            resList.add(res);
        }

        assertTrue(resList.containsAll(animals));
        assertFalse(resList.contains(grass));
        assertFalse(resList.contains(stone));
        assertEquals(3, resList.size());
    }

}
