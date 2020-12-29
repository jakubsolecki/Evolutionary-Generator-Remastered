import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.jakubsolecki.model.Vector2D;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        // TODO
    }
}
