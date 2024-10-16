package grid;

import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GridJsonIO {
public void saveToJSON(Grid grid) {
		try {
            // Create a Gson instance with pretty printing
            Gson gson = new GsonBuilder().create();


            // Convert the Java object to JSON and write it to a file
            FileWriter writer = new FileWriter("src/main/resources/stored_grids.json", true);
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


    return new Grid();
}

}
