import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import grid.Grid;
import grid.GridJsonIO;

public class GridJsonIOTest {
    @Test
    public void testSizeHolding() {
        Grid in = new Grid("testgrid", 2);
        GridJsonIO.saveToJSON(in);
        Grid out = GridJsonIO.loadFromJSON("testgrid");
        assertEquals(2, out.getGridSizeBase());  
    }
    
    public void testValueHolding() {
        Grid in = new Grid("testgrid", 2);
        in.setValue(0, 0, 3);
        GridJsonIO.saveToJSON(in);
        Grid out = GridJsonIO.loadFromJSON("testgrid");
        assertEquals(3, out.getValue(0, 0));  
    }

    @Test
    public void testHardcodingHolding() {
        Grid in = new Grid("testgrid", 2);
        in.setIsHardCoded(0, 0, true);
        GridJsonIO.saveToJSON(in);
        Grid out = GridJsonIO.loadFromJSON("testgrid");
        assertEquals(true, out.getIsHardCoded(0, 0));
    }
}