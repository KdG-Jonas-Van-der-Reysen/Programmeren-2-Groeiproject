import be.kdg.model.Brommer;
import be.kdg.model.BrommerKlasse;
import persist.BrommerDao;
import persist.BrommerDbDao;

import java.time.LocalDate;

public class Demo_6 {
    public static void main(String[] args) {
        System.out.println("Hello!");

        BrommerDbDao dao = new BrommerDbDao("db/brommersdb");
        dao.insert(new Brommer(1, "Honda", "123456789", 100, 0, BrommerKlasse.A, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1)));
        dao.insert(new Brommer(2, "Honda", "123456789", 99, 5, BrommerKlasse.A, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1)));

        // Get all brommers
        System.out.println("Sorted on gewicht");
        dao.sortedOnGewicht().forEach(System.out::println);

        System.out.println();
        System.out.println("Sorted on aantalKeerOnderhoud");
        dao.sortedOnAantalKeerOnderhoud().forEach(System.out::println);

        Brommer brommer2 = dao.sortedOnGewicht().get(1);

        brommer2.setModel("Iets compleet anders");
        dao.update(brommer2);

        System.out.println();
        System.out.println("Sorted on gewicht");
        dao.sortedOnGewicht().forEach(System.out::println);
    }
}
