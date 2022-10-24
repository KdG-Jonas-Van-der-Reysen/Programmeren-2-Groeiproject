import be.kdg.data.Data;
import be.kdg.model.*;
import be.kdg.util.BrommerFunctions;

import java.util.*;
import java.util.stream.Collectors;

public class Demo_4 {
    private static void printBrommers(List<Brommer> brommers) {
        brommers.stream()
                .forEach(System.out::println);
    }
    public static void main(String[] args) {
        // Instance of multi class
        Brommers brommers = new Brommers();

        // Get brommers from data class
        List<Brommer> brommerList = Data.getData();
        System.out.println("Brommers: " + brommerList);

        // Add brommers to brommers class
        brommerList.stream()
                .forEach(b -> brommers.add(b));

        // Print brommers
        System.out.println("\nNiet gesorteerd\n=============================================");
        printBrommers(brommerList);

        // Sort brommers by weight
        List<Brommer> sortedByWeight = brommers.sortedBy(Brommer::getGewicht);
        System.out.println("\n\nGesorteerd op gewicht\n=============================================");
        printBrommers(sortedByWeight);

        // Sort brommers by last maintenance
        List<Brommer> sortedByLastMaintenance = brommers.sortedBy(Brommer::getLaatsteOnderhoud);
        System.out.println("\n\nGesorteerd op laatste onderhoud\n=============================================");
        printBrommers(sortedByLastMaintenance);

        // Sort brommers by release date
        List<Brommer> sortedByReleaseDate = brommers.sortedBy(Brommer::getReleaseDate);
        System.out.println("\n\nGesorteerd op release date\n=============================================");
        printBrommers(sortedByReleaseDate);


        // FILTERING
        // Filter brommers by class
        List<Brommer> filteredByClass = BrommerFunctions.filteredList(brommerList, brommer -> brommer.getKlasse().equals(BrommerKlasse.A));
        System.out.println("\n\nGefilterd op klasse A\n=============================================");
        printBrommers(filteredByClass);

        // Filter brommers by price > 3000
        List<Brommer> filteredByPrice = BrommerFunctions.filteredList(filteredByClass, brommer -> brommer.getGewicht() > 2500);
        System.out.println("\n\nKlasse A brommers zwaarder dan 2.5 ton\n=============================================");
        printBrommers(filteredByPrice);

        // Filter brommers by last maintenance in 2022
        List<Brommer> filteredByLastMaintenance = BrommerFunctions.filteredList(filteredByPrice, brommer -> brommer.getLaatsteOnderhoud().getYear() == 2018);
        System.out.println("\n\nKlasse A brommers zwaarder dan 2.5 ton die het laatst onderhouden zijn in 2018\n=============================================");
        printBrommers(filteredByLastMaintenance);

        // AVERAGES
        System.out.println("\n\nAnalytische data\n=============================================");

        // Get brommers from data class
        List<Brommer> nieuweBrommerList = Data.getData();

        // Gemiddeld gewicht
        System.out.printf("Gemiddeld gewicht van de brommers: %.1fkg\n", BrommerFunctions.average(nieuweBrommerList, Brommer::getGewicht));

        // Gemiddeld aantal keer onderhoud
        System.out.printf("Gemiddeld aantal keer onderhoud van de brommers: %.2f keer\n", BrommerFunctions.average(nieuweBrommerList, Brommer::getAantalKeerOnderhoud));


        // Count if
        System.out.println("\n\nCountIf\n=============================================");
        System.out.println("Aantal brommers met een gewicht van meer dan 2.5 ton: " + BrommerFunctions.countIf(nieuweBrommerList, brommer -> brommer.getGewicht() > 2500));


        // 3. STREAMS
        System.out.println("\n\nStreams\n=============================================");
        List<Brommer> strBrommers = Data.getData();

        // Amount of "brommers" released after 2000
        long amount = strBrommers.stream().filter(brommer -> brommer.getReleaseDate().getYear() >= 2000).count();
        System.out.printf("Aantal brommers uitgebracht in of na 2000: %d\n", amount);

        // First sort on weight, then on last maintenance
        System.out.println("\n\nGesorteerd op gewicht, dan op laatste onderhoud");
        strBrommers.stream()
                .sorted(Comparator.comparing(Brommer::getGewicht).thenComparing(Brommer::getLaatsteOnderhoud))
                .forEach(System.out::println);

        // Make model name uppercase, remove doubles, sort in descending order and put the elements in one string
        System.out.println("\n\nAlle brommers in hoofdletters");
        String brommerModels = strBrommers.stream()
                .map(brommer -> brommer.getModel().toUpperCase())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(", "));
        System.out.println(brommerModels);

        // Filter op een criterium en pik er eentje uit met findAny
        System.out.println("\nEen willekeurige brommer die meer dan 1 keer op onderhoud geweest is:");
        Optional<Brommer> anyBrommer = strBrommers.stream()
                .filter(brommer -> brommer.getAantalKeerOnderhoud() > 1)
                .findAny();

        if(anyBrommer.isPresent()) System.out.println(anyBrommer.get());

        // Kampioenen
        // Zwaarste brommer
        Brommer zwaarsteBrommer = strBrommers.stream()
                .max(Comparator.comparing(Brommer::getGewicht))
                .get();

        System.out.println("\n\nZwaarste brommer:");
        System.out.println(zwaarsteBrommer);

        // Kampioen in slecht onderhouden
        Brommer bestOnderhoudenBrommer = strBrommers.stream()
                .max(Comparator.comparing(Brommer::getAantalKeerOnderhoud))
                .get();

        System.out.println("\n\nBest onderhouden brommer:");
        System.out.println(bestOnderhoudenBrommer);

        // Filter op een string criterium, map elk element op een string (bijvoorbeeld op naam),
        // sorteer alfabetisch en collect als een list van string
        System.out.println("\n\nLijst met gesorteerde brommers van Segway: ");
        List<String> segwayBrommers = strBrommers.stream()
                .filter(brommer -> brommer.getModel().toLowerCase().contains("segway"))
                .map(brommer -> brommer.getModel())
                .sorted()
                .collect(Collectors.toList());

        System.out.printf("[%s]", String.join(", ",segwayBrommers));


        // Gepartitioneerd
        Map<Boolean, List<Brommer>> gepartitioneerdeBrommers = strBrommers.stream()
                .sorted(Comparator.comparing(Brommer::getLaatsteOnderhoud))
                .collect(Collectors.partitioningBy(a -> a.getLaatsteOnderhoud().getYear() >= 2000));

        System.out.println("\n\nSublist met brommers die laatst onderhouden werden VOOR 2000: ");
        printBrommers(gepartitioneerdeBrommers.get(false));
        System.out.println("\n\nSublist met brommers die laatst onderhouden werden IN of NA 2000: ");
        printBrommers(gepartitioneerdeBrommers.get(true));
        
        
        // Gegroepeerd
        System.out.println("\n\nAlle brommers per klasse (A/B)");
        Map<BrommerKlasse, List<Brommer>> gegroepeerdeBrommers = new TreeMap(
                strBrommers.stream()
                        .sorted(Comparator.comparing(Brommer::getModel))
                        .collect(Collectors.groupingBy(Brommer::getKlasse))
        );

        gegroepeerdeBrommers.keySet().stream()
            .forEach(key -> {
                System.out.printf("%s: ",key);
                var groupedModelNames = gegroepeerdeBrommers.get(key).stream()
                        .map(Brommer::getModel)
                        .collect(Collectors.toList());
                System.out.print(String.join(", ", groupedModelNames));
                System.out.println();
            });
    }
}
