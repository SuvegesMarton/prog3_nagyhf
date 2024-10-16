package main;

import grid.Grid;
import grid.GridJsonIO;

class Main {
    public static void main(String[] args) {
		Grid a = new Grid(3);
		GridJsonIO io = new GridJsonIO();
		io.saveToJSON(a);
	}
}