import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jakubsolecki.model.Vector2D;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class Vector2DTest {

    @Test
    public void isLowerLeftTest() {
        // given
        Vector2D vectBase = new Vector2D(2, 2);
        Vector2D vectOk = new Vector2D(1, 2);
        Vector2D vectOk2 = new Vector2D(1, 1);
        Vector2D vectOk3 = new Vector2D(2, 1);
        Vector2D vectNotOk = new Vector2D(3, 3);
        Vector2D vectNotOk2 = new Vector2D(3, 2);
        Vector2D vectNotOk3 = new Vector2D(2, 3);

        // when then
        assertTrue(vectOk.isLowerLeft(vectBase));
        assertTrue(vectOk2.isLowerLeft(vectBase));
        assertTrue(vectOk3.isLowerLeft(vectBase));
        assertFalse(vectNotOk.isLowerLeft(vectBase));
        assertFalse(vectNotOk2.isLowerLeft(vectBase));
        assertFalse(vectNotOk3.isLowerLeft(vectBase));
    }

    @Test
    public void isUpperRightTest() {
        // given
        Vector2D vectBase = new Vector2D(1, 1);
        Vector2D vectOk = new Vector2D(1, 2);
        Vector2D vectOk2 = new Vector2D(1, 1);
        Vector2D vectOk3 = new Vector2D(2, 1);
        Vector2D vectNotOk = new Vector2D(0, 0);
        Vector2D vectNotOk2 = new Vector2D(0, 1);
        Vector2D vectNotOk3 = new Vector2D(1, 0);

        // when then
        assertTrue(vectOk.isUpperRight(vectBase));
        assertTrue(vectOk2.isUpperRight(vectBase));
        assertTrue(vectOk3.isUpperRight(vectBase));
        assertFalse(vectNotOk.isUpperRight(vectBase));
        assertFalse(vectNotOk2.isUpperRight(vectBase));
        assertFalse(vectNotOk3.isUpperRight(vectBase));
    }

    @Test
    public void translateToBoardTest() {
        // given
        Vector2D vect1 = new Vector2D(100, 99);
        Vector2D vect2 = new Vector2D(99, 100);
        Vector2D vect3 = new Vector2D(-1, 99);
        Vector2D vect4 = new Vector2D(100, 100);

        // when
        Vector2D translatedVect1 = vect1.translateToBoard(100, 100);
        Vector2D translatedVect2 = vect2.translateToBoard(100, 100);
        Vector2D translatedVect3 = vect3.translateToBoard(100, 100);
        Vector2D translatedVect4 = vect4.translateToBoard(100, 100);

        // then
        assertEquals(0, translatedVect1.X);
        assertEquals(99, translatedVect1.Y);
        assertEquals(99, translatedVect2.X);
        assertEquals(0, translatedVect2.Y);
        assertEquals(99, translatedVect3.X);
        assertEquals(99, translatedVect3.Y);
        assertEquals(0, translatedVect4.X);
        assertEquals(0, translatedVect4.Y);
    }


}
