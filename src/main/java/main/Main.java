package main;

import grid.Grid;
import grid.GridJsonIO;

class Main {
    public static void main(String[] args) {
		Grid a = new Grid("monke", 3);
		GridJsonIO io = new GridJsonIO();
		io.loadFromJSON("monke");
	}
}