package grid;


import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * class for handling grid saving and loading with JSON.
 */
public class GridJsonIO {
    private static final Type gridListType = new TypeToken<List<Grid>>(){}.getType();
    private static final String ioFilePath = "src/main/resources/stored_grids.json";

    /**
     * save a grid to a json file. if a grid with that name is already saved, overwrite it. If the file does not exist, create a new empty file and save the grid in it.
     */
    public static boolean saveToJSON(Grid grid) {
		try {
            // Create Gson instance
            Gson gson = new Gson();

            // Read the JSON array from file and convert it to a list of grid objects
            FileReader reader = new FileReader(ioFilePath);
            List<Grid> grids = gson.fromJson(reader, gridListType);
            reader.close();
            
            //Try to find the searched ID
            boolean found = false;
            for (int i=0; i<grids.size(); i++) {
                if (grids.get(i).getID().equals(grid.getID())) { //
                    found = true;
                    grids.set(i, grid);
                    break;
                }
            }
            if (!found) {
                grids.add(grid);
            }

            // Convert the Java object to JSON and write it to a file
            FileWriter writer = new FileWriter(ioFilePath);
            gson.toJson(grids, writer);
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
	}
    /**
     * load a grid from a JSON file. If a grid with the specified name does not exist, create a new standard grid (3x3 grid size base) with the given name.
     * If the file does not exists, also return the default grid.
     */
    public static Grid loadFromJSON(String id) {
        try {
            // Create Gson instance
            Gson gson = new Gson();

            // Read the JSON array from file and convert it to a list of Grid objects
            File file = new File(ioFilePath); // ensure the file exists
            if (!file.exists()) {
                return new Grid(id, 3);
            }
            FileReader reader = new FileReader(ioFilePath);
            List<Grid> grids = gson.fromJson(reader, gridListType);
            reader.close();

            // Iterate on the Grids list to find the correct ID.
            for (Grid g : grids) {
                if (g.getID().equals(id)) {
                    return g;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If the search did not succeed, or some Exceptions were thrown&catched, create a "default" empty Grid.
        return new Grid(id, 3);

    }
}
