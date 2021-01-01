import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jakubsolecki.model.Vector2D;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class Vector2DTest {

    @Test
    public void isLowerLeftTest() {
        // given
        var vectBase = new Vector2D(2, 2);
        var vectOk = new Vector2D(1, 2);
        var vectOk2 = new Vector2D(1, 1);
        var vectOk3 = new Vector2D(2, 1);
        var vectNotOk = new Vector2D(3, 3);
        var vectNotOk2 = new Vector2D(3, 2);
        var vectNotOk3 = new Vector2D(2, 3);

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
        var vectBase = new Vector2D(1, 1);
        var vectOk = new Vector2D(1, 2);
        var vectOk2 = new Vector2D(1, 1);
        var vectOk3 = new Vector2D(2, 1);
        var vectNotOk = new Vector2D(0, 0);
        var vectNotOk2 = new Vector2D(0, 1);
        var vectNotOk3 = new Vector2D(1, 0);

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
        var vect1 = new Vector2D(100, 99);
        var vect2 = new Vector2D(99, 100);
        var vect3 = new Vector2D(-1, 99);
        var vect4 = new Vector2D(100, 100);

        // when
        var translatedVect1 = vect1.translateToBoard(100, 100);
        var translatedVect2 = vect2.translateToBoard(100, 100);
        var translatedVect3 = vect3.translateToBoard(100, 100);
        var translatedVect4 = vect4.translateToBoard(100, 100);

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
