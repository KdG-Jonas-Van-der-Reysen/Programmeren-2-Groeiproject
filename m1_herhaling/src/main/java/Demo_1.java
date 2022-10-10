import be.kdg.data.Data;
import be.kdg.model.*;

import java.time.LocalDate;
import java.util.*;

public class Demo_1 {
    public static void main(String[] args) {
        // Instance of multi class
        Brommers brommers = new Brommers();

        // Get brommers from data class
        List<Brommer> brommerList = Data.getData();
        System.out.println("Brommers: " + brommerList);

        // Add brommers to brommers class
        for (Brommer brommer : brommerList) {
            brommers.add(brommer);
        }

        // Add double object
        brommers.add(brommerList.get(2));

        // Print brommers
        System.out.println("\n\n==================== BROMMERS ====================");
        for (Brommer brommer : brommers.getBrommers()) {
            System.out.println(brommer);
        }

        // Print sorted on gewicht
        System.out.println("\n\n==================== SORTED ON GEWICHT ====================");
        for (Brommer brommer : brommers.sortedOnGewicht()) {
            System.out.println(brommer);
        }

        // Print sorted on laatste onderhoud
        System.out.println("\n\n==================== SORTED ON LAATSTE ONDERHOUD ====================");
        for (Brommer brommer : brommers.sortedOnLaatsteOnderhoud()) {
            System.out.println(brommer);
        }

        // Print sorted on release date
        System.out.println("\n\n==================== SORTED ON RELEASE DATE ====================");
        for (Brommer brommer : brommers.sortedOnReleaseDate()) {
            System.out.println(brommer);
        }

        // Test it out!
        Brommer brommer1 = new Brommer("Honda","LAKJFLEAKZJ", 100, 0, BrommerKlasse.A, LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 1));
        Brommer brommer2 = new Brommer();

        System.out.println("\n\n==================== TESTING CONSTRUCTORS ====================");
        System.out.println(brommer1);
        System.out.println(brommer2);

        System.out.println("\n\n==================== TESTING EXCEPTION ====================");
        // Test illegal argument selection
        try {
            Brommer brommer3 = new Brommer("Honda","LAKJFLEAKZJ", 100, -1, BrommerKlasse.A, LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 1));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }



    }
}
