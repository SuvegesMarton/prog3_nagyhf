import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import grid.Grid;
import grid.ValidityChecks;

public class ValidityChecksTest {
    @Test
    public void testBoxIllegal() {
        Grid g = new Grid("testgrid", 2);
        g.setValue(0, 0, 1);
        g.setValue(1, 1, 1);

        assertEquals(false, ValidityChecks.checkBoxRule(g));
        assertEquals(true, ValidityChecks.checkColumnRule(g));
        assertEquals(true, ValidityChecks.checkRowRule(g));

    }

    @Test
    public void testColumnIllegal() {
        Grid g = new Grid("testgrid", 2);
        g.setValue(0, 0, 1);
        g.setValue(0, 2, 1);

        assertEquals(false, ValidityChecks.checkColumnRule(g));
        assertEquals(true, ValidityChecks.checkBoxRule(g));
        assertEquals(true, ValidityChecks.checkRowRule(g));


    }

    @Test
    public void testRowIllegal() {
        Grid g = new Grid("testgrid", 2);
        g.setValue(0, 0, 1);
        g.setValue(2, 0, 1);

        assertEquals(false, ValidityChecks.checkRowRule(g));
        assertEquals(true, ValidityChecks.checkColumnRule(g));
        assertEquals(true, ValidityChecks.checkBoxRule(g));


    }

    @Test
    public void testEmpty() {
        Grid g = new Grid("testgrid", 2);
        assertEquals(true, ValidityChecks.checkColumnRule(g));
        assertEquals(true, ValidityChecks.checkRowRule(g));
        assertEquals(true, ValidityChecks.checkBoxRule(g));


    }

}