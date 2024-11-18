import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import grid.Grid;

public class GridTest {
    @Test
    public void testInitialization() {
        Grid g = new Grid("testgrid");
        assertEquals(3, g.getGridSizeBase());
    }

    @Test
    public void testSetValue() {
        Grid g = new Grid("testgrid");
        g.setValue(3, 4, 5);
        assertEquals(5, g.getValue(3,4));
    }

    @Test
    public void testSetValue2() {
        Grid g = new Grid("testgrid", 2);
        assertThrows(IllegalArgumentException.class, ()->{g.setValue(3, 4, 5);});
    }

    @Test
    public void testEmptyLegal() {
        Grid g = new Grid("testgrid", 2);
        assertEquals(true, g.isLegal());
    }

    @Test
    public void testIllegal() {
        Grid g = new Grid("testgrid", 2);
        g.setValue(0, 0, 1);
        g.setValue(1, 0, 1);
        g.setValue(0, 1, 1);

        assertEquals(false, g.isLegal());
    }

}