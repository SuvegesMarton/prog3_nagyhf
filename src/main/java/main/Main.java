package main;

import grid.Grid;
import grid.GridJsonIO;
import gui.GridGui;

class Main {
    public static void main(String[] args) {
		GridJsonIO io = new GridJsonIO();
		Grid b = io.loadFromJSON("monke");
		GridGui gg = new GridGui(b);
		gg.makeWindow();
		


	}
}