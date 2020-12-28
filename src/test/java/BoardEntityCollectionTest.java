import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jakubsolecki.model.*;
import pl.jakubsolecki.service.BoardEntityCollection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
        Vector2D pos = new Vector2D(1, 1);
        Animal animal = Mockito.mock(Animal.class);
        when(animal.getPosition()).thenReturn(pos);

        // when
        entityCollection.add(animal);

        // then
        assertTrue(entityCollection.hasKey(pos));
        assertEquals(animal, entityCollection.entitiesAt(pos).get(0));
    }

    @Test
    public void addGrassTest() {
        // given
        Vector2D pos = new Vector2D(1, 1);
        Grass grass = Mockito.mock(Grass.class);
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
        Vector2D pos = new Vector2D(1, 1);
        Stone stone = Mockito.mock(Stone.class);
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
        Vector2D pos = new Vector2D(1, 1);
        Animal animal = Mockito.mock(Animal.class);
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
        Vector2D pos = new Vector2D(1, 1);
        Grass grass = Mockito.mock(Grass.class);
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
        Vector2D pos = new Vector2D(1, 1);
        Stone stone = Mockito.mock(Stone.class);
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
        List<Animal> animals = Arrays.asList(
                Mockito.mock(Animal.class),
                Mockito.mock(Animal.class),
                Mockito.mock(Animal.class)
        );

        Grass grass = Mockito.mock(Grass.class);
        when(grass.getPosition()).thenReturn(new Vector2D(5, 5));

        Stone stone = Mockito.mock(Stone.class);
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
            System.out.println(it.next().getValue());
        }
    }

}
