package main;

import grid.Grid;
import grid.GridJsonIO;

class Main {
    public static void main(String[] args) {
		GridJsonIO io = new GridJsonIO();

		Grid a = new Grid("monke", 3);
		a.setValue(3, 2, 6);
		io.saveToJSON(a);

		Grid b = io.loadFromJSON("monke");
		Grid c = io.loadFromJSON("macska");
		System.out.println(b.getValue(3, 2));
		System.out.println(c.getValue(3, 2));

	}
}