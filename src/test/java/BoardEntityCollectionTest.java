import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.jakubsolecki.model.Animal;
import pl.jakubsolecki.model.Grass;
import pl.jakubsolecki.model.Stone;
import pl.jakubsolecki.model.Vector2D;
import pl.jakubsolecki.service.BoardEntityCollection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Animal animal = Animal.builder().position(pos).build();

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
        Grass grass = new Grass(10, pos);

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
        Stone stone = new Stone(pos);

        // when
        entityCollection.add(stone);

        // then
        assertTrue(entityCollection.hasKey(pos));
        assertEquals(stone, entityCollection.entitiesAt(pos).get(0));
    }
}
