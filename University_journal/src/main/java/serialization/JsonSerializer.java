package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonSerializer <T> {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> void writeToJsonFile(ArrayList<T> list, String filePath) {

        try (FileWriter jsonFile = new FileWriter(filePath)) {
            gson.toJson(list, jsonFile);
        } catch (IOException e) {
            System.out.println("Error with serialization to json file. " + e.getMessage());
        }
    }
}
