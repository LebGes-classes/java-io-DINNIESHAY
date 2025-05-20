package serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonDeserializer<T> {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> ArrayList<T> readFromJsonFile(Class<T> classOfT, String filePath) {
        ArrayList<T> list = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = TypeToken.getParameterized(ArrayList.class, classOfT).getType();
            list =  gson.fromJson(reader, listType);
            return list;
        } catch (IOException e) {
            System.out.println("Error with deserialization json file. " + e.getMessage());
        }

        return list;
    }
}
