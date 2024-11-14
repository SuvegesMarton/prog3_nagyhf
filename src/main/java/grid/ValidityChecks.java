package grid;

import java.util.HashSet;
import java.util.Set;

public class ValidityChecks {
    public static boolean checkColumnRule(Grid g) {
        int sideLength = g.getGridSizeBase()*g.getGridSizeBase();
		//check columns
		for (int x=0; x<sideLength;x++) {
			for (int y=0; y<sideLength;y++){
				int checkedValue = g.getValue(x, y);
				if (checkedValue==0) {
					continue;
				}
				for (int temp_y=y+1;temp_y<sideLength;temp_y++) {
					if (g.getValue(x, temp_y) == checkedValue) {
						return false;
					}
				}
            }
		}
        return true;
    }


    public static boolean checkRowRule(Grid g) {
        int sideLength = g.getGridSizeBase()*g.getGridSizeBase();
		//check rows
		for (int y=0; y<sideLength;y++) {
			for (int x=0; x<sideLength;x++){
				int checkedValue = g.getValue(x, y);
				if (checkedValue==0) {
					continue;
				}
				for (int temp_x=x+1;temp_x<sideLength;temp_x++) {
					if (g.getValue(temp_x, y) == checkedValue) {
						return false;
					}	
				}
			}
		}
        return true;
    }


    public static boolean checkBoxRule(Grid g) {
        int gsb = g.getGridSizeBase();
        int sideLength = gsb*gsb;
        Set<Integer> valuesInBox = new HashSet<>();
		for (int y=0; y<gsb;y++) {
			for (int x=0; x<gsb;x++){
				//collect the values from the box into a set to make rule checking easier
				for (int yInBox=0;yInBox<gsb;yInBox++) {
					for (int xInBox=0;xInBox<gsb;xInBox++) {
                        //perform the check
                        int valueAtIndex = g.getValue(gsb*x+xInBox, gsb*y+yInBox);
                        if (valuesInBox.contains(valueAtIndex)) {return false;}
						if (valueAtIndex != 0) {valuesInBox.add(g.getValue(gsb*x+xInBox, gsb*y+yInBox));}
					}
				}
                valuesInBox.clear();
			}
		}
        return true;
    }
}