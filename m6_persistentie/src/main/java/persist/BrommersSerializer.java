package persist;

import be.kdg.model.Brommer;
import be.kdg.model.Brommers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class BrommersSerializer {
    private final String FILENAME;

    public BrommersSerializer(String filename) {
        this.FILENAME = filename;
    }

    public void serialize(Brommers brommers) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("db/" + FILENAME));
        oos.writeObject(brommers.getBrommers());
    }

    public Brommers deserialize() throws IOException, ClassNotFoundException {
        // Make new list
        TreeSet<Brommer> brommers;

        // Define the input stream
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("db/" + FILENAME));

        // Read the list
        brommers = (TreeSet<Brommer>) ois.readObject();

        // Construt new Brommers object
        Brommers brommersObject = new Brommers();

        brommers.forEach(brommersObject::add);

        return brommersObject;
    }

}
