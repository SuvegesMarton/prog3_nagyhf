package grid;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Grid {
	private String id;
	private final int gridSizeBase;
	private int[] values;
	private boolean[] isHardcoded;
	private static String ioFilePath = "src/main/resources/stored_grids.json";

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

    public void saveToJSON() {
try {
            // Create a Gson instance with pretty printing
            Gson gson = new GsonBuilder().create();


            // Convert the Java object to JSON and write it to a file
            FileWriter writer = new FileWriter("src/main/resources/stored_grids.json", false);
            gson.toJson(this, writer);

            // Close the writer
            writer.close();

            System.out.println("JSON written to file successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
}