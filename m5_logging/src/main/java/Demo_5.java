import be.kdg.model.Brommer;
import be.kdg.model.BrommerKlasse;
import be.kdg.model.Brommers;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.LogManager;

public class Demo_5 {
    public static void main(String[] args) {
        loadLoggingConfiguration();

        Brommer brommer1 = new Brommer("", "3LNHM26T77R671336", 2300.12, 2, BrommerKlasse.A, LocalDate.of(2003, 5, 17), LocalDate.of(2014, 11, 25));
        Brommer brommer2 = new Brommer("Sym orbit 3", "3LNHM26T77R671336", -2300.12, 2, BrommerKlasse.A, LocalDate.of(2003, 5, 17), LocalDate.of(2014, 11, 25));
        Brommer brommer3 = new Brommer("Sym orbit 3", "3LNHM26T77R671336", -2300.12, -2, BrommerKlasse.A, LocalDate.of(2003, 5, 17), LocalDate.of(2014, 11, 25));
        Brommer brommer4 = new Brommer("Sym orbit 3", "", -2300.12, -2, BrommerKlasse.A, LocalDate.of(2003, 5, 17), LocalDate.of(2014, 11, 25));

        Brommer brommer5 = new Brommer("Segway E125S", "3GYFK66N83G231247", 3599, 3, BrommerKlasse.B, LocalDate.of(2019, 1, 14), LocalDate.of(2022, 8, 22));
        Brommer brommer6 = new Brommer("Segway E110SE", "1B3CC4FB6AN100405", 2499, 2, BrommerKlasse.B, LocalDate.of(2003, 5, 17), LocalDate.of(2022, 3, 21));
        Brommer brommer7 = new Brommer("Segway E110S", "2G4WS52J421173448", 2299, 1, BrommerKlasse.B, LocalDate.of(1927, 9, 29), LocalDate.of(2022, 5, 20));
        Brommer brommer8 = new Brommer("Segway B110S", "KMHTC6AD9DU186351", 1779, 0, BrommerKlasse.B, LocalDate.of(2013, 3, 18), LocalDate.of(2013, 4, 25));
        Brommer brommer9 = new Brommer("Brekr Model B", "1FTJW36F6TEB28864", 4749, 3, BrommerKlasse.B, LocalDate.of(2016, 5, 7), LocalDate.of(2015, 7, 25));

        Brommers brommers = new Brommers();
        brommers.add(brommer5);
        brommers.add(brommer6);
        brommers.add(brommer7);
        brommers.add(brommer8);
        brommers.add(brommer9);

        brommers.remove(brommer6);
        brommers.remove(brommer9);

    }

    private static void loadLoggingConfiguration() {
        InputStream inputStream = Demo_5.class.getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch(IOException e) {
            System.err.println("Logging configuratiebestand kan niet geladen worden");
        }
    }
}
