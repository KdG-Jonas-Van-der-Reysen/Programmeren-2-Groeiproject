package parsing;

import be.kdg.gson.LocalDateGsonAdapter;
import be.kdg.model.Brommer;
import be.kdg.model.Brommers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrommersGsonParser {
    public static void writeJson(Brommers brommers, String fileName) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter()).create();

        String brommersJson = gson.toJson(brommers);
        System.out.println(brommersJson);

        try (PrintWriter jsonWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
            jsonWriter.write(brommersJson);

            System.out.println("Json file saved");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Brommers readJson(String fileName) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter()).create();

        List<Brommer> brommerList = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Brommers brommers = gson.fromJson(reader, Brommers.class);


            return brommers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
