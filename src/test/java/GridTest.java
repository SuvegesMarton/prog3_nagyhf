import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import grid.Grid;

public class GridTest {

    @Test
    public void testAddition() {
        Grid g = new Grid("testgrid");
        assertEquals(5, 5);
    }
}