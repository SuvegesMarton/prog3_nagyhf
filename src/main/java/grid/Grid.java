package grid;

import java.io.*;

public class Grid {
	private final int gridSizeBase;
	private int[] values;
	private boolean[] isHardcoded;
	private static String ioFilePath = "src/main/resources/stored_grids.txt";

	public Grid(int gridSizeBase) {
		this.gridSizeBase = gridSizeBase;
		int numberOfFields = (int)Math.pow(gridSizeBase, 4);
		this.values = new int[numberOfFields];
		this.isHardcoded = new boolean[numberOfFields];
	}

	private int xyToScalar(int x, int y) {
		return y*this.gridSizeBase*this.gridSizeBase + x;
	}

	public int getGridSizeBase() {
		return this.gridSizeBase;
	}

	public int getValue(int x, int y) {
		return this.values[xyToScalar(x, y)];
	}
	
	public boolean getIsHardCoded(int x, int y) {
		return this.isHardcoded[xyToScalar(x, y)];
	}

	public void setValue(int x, int y, int newValue) {
		if (newValue<0 || this.gridSizeBase*this.gridSizeBase<newValue) {
			throw new IllegalArgumentException("Field value out of the legal domain.");
		}
		this.values[xyToScalar(x, y)] = newValue;
	}
	
	public void setIsHardCoded(int x, int y, boolean newSetting) {
		this.isHardcoded[xyToScalar(x, y)] = newSetting;
	}

    public void saveToFile(int saveId) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(ioFilePath, true))) { //open in append mode
			//write ID
			writer.println("\nGRID ID: " + saveId);
			//write grid size base
			writer.println("GRID SIZE BASE: " + this.gridSizeBase);
			//write values
			for (int n : this.values) {
				writer.print(n);
			}
			writer.println();
			//write is-hard-coded values
			for (boolean b : this.isHardcoded) {
				if (b) {writer.print("1");}
				else {writer.print("0");}
			}
			writer.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}