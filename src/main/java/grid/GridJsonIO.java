package grid;


import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;


public class GridJsonIO {
    private static String ioFilePath = "src/main/resources/stored_grids.json";

public void saveToJSON(Grid grid) {
		try {
            // Create a Gson instance with pretty printing
            Gson gson = new GsonBuilder().create();

            // Convert the Java object to JSON and write it to a file
            FileWriter writer = new FileWriter(ioFilePath, true);
            gson.toJson(grid, writer);
            writer.write('\n');
            // Close the writer
            writer.close();

            System.out.println("JSON written to file successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

public Grid loadFromJSON(String id) {
    try {
            // Create Gson instance
            Gson gson = new Gson();

            // Define the type for List<Person>
            Type gridListType = new TypeToken<List<Grid>>(){}.getType();

            // Read the JSON array from file and convert it to a list of Person objects
            FileReader reader = new FileReader(ioFilePath);
            List<Grid> grids = gson.fromJson(reader, gridListType);

            // Close the reader
            reader.close();

            // Access the list of people
            for (Grid g : grids) {
                if (g.getID().equals(id)) return g;
            }

            return new Grid("a",1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Grid("b",3);
    }
}
