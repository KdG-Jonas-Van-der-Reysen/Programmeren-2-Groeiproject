
import be.kdg.kollections.lists.ArrayList;
import be.kdg.kollections.Kollections;
import be.kdg.kollections.lists.LinkedList;
import be.kdg.kollections.lists.List;
import be.kdg.kollections.maps.HashMap;
import be.kdg.kollections.maps.ListMap;
import be.kdg.kollections.maps.Map;
import be.kdg.kollections.sets.ArraySet;
import be.kdg.kollections.sets.Set;
import be.kdg.kollections.sets.TreeSet;
import be.kdg.model.Brommer;
import be.kdg.model.BrommerFactory;
import be.kdg.model.BrommerKlasse;

import java.time.LocalDate;
import java.util.Random;

public class PerformanceTester {

    public static List<Brommer> randomList(int n) {
        List<Brommer> myList = new LinkedList<>();
        new Random().ints(n).forEach(i -> myList.add(BrommerFactory.newRandomBrommer()));
        return myList;
    }


    public static void compareArrayListAndLinkedList(int n) {
        // ADD AT BEGINNING
        long addBeginALStart = System.currentTimeMillis();
        ArrayList<Brommer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(0, BrommerFactory.newRandomBrommer());
        }
        long addBeginALDuration = System.currentTimeMillis()-addBeginALStart;

        long addBeginLLStart = System.currentTimeMillis();
        LinkedList<Brommer> linkedList = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            linkedList.add(0, BrommerFactory.newRandomBrommer());
        }
        long addBeginLLDuration = System.currentTimeMillis()-addBeginLLStart;

        // GET AT END
        long getEndALStart = System.currentTimeMillis();
        Brommer b1 = arrayList.get(arrayList.size()-1);
        long getEndALDuration = System.currentTimeMillis()-getEndALStart;

        long getEndLLStart = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            linkedList.get(i);
        }
        long getEndLLDuration = System.currentTimeMillis()-getEndLLStart;

        System.out.println("Adding " + n + " to ArrayList: " + addBeginALDuration + "ms");
        System.out.println("Adding " + n + " to LinkedList: " + addBeginLLDuration + "ms");
        System.out.println("Getting " + n + " element from ArrayList: " + getEndALDuration + "ms");
        System.out.println("Getting " + n + " element from LinkedList: " + getEndLLDuration + "ms");

    }

    public static void compareListMapToHashMap(int n) {
        long start;
        long duration;

        Map<Brommer, String> listMap = new ListMap<>();
        Map<Brommer, String> hashMap= new HashMap<>(n);

        fillMap(listMap, n);
        fillMap(hashMap, n);

        //Listmap test
        Brommer.equalsCounter = 0;
        start = System.nanoTime();
        performGetOnMap(listMap,n);
        duration = System.nanoTime()-start;
        System.out.printf("%nListmap: n = %5d, equalscount = %8d, nanosec = %15d",n,Brommer.equalsCounter,duration);

        //Hashmap test
        Brommer.equalsCounter = 0;
        start = System.nanoTime();
        performGetOnMap(hashMap, n);
        duration = System.nanoTime()-start;
        System.out.printf("%nHashmap: n = %5d, equalscount = %8d, nanosec = %15d",n,Brommer.equalsCounter,duration);

    }

    public static void compareArraySetToTreeSet(int n) {
        long start;
        long end;
        Set<Brommer> arraySet = new ArraySet<>();
        Set<Brommer> treeSet= new TreeSet<>();

        // ArraySet test
        Brommer.equalsCounter = 0;
        Brommer.compareCounter = 0;
        start = System.nanoTime();
        new Random().ints(n).forEach(i -> arraySet.add(BrommerFactory.newRandomBrommer()));
        end = System.nanoTime();
        System.out.printf("%nArraySet, n = %5d: equalscount : %-7d",n, Brommer.equalsCounter);
        System.out.printf("%nArraySet, n = %5d: comparecount: %-7d",n, Brommer.compareCounter);
        System.out.printf("%nArraySet, n = %5d: nanosec     : %-7d",n, end-start);


        // TreeSet test
        Brommer.equalsCounter = 0;
        Brommer.compareCounter = 0;
        start = System.nanoTime();
        new Random().ints(n).forEach(i -> treeSet.add(BrommerFactory.newRandomBrommer()));
        end = System.nanoTime();
        System.out.printf("%nTreeSet,  n = %5d: equalscount : %-7d",n, Brommer.equalsCounter);
        System.out.printf("%nTreeSet,  n = %5d: comparecount: %-7d",n, Brommer.compareCounter);
        System.out.printf("%nTreeSet,  n = %5d: nanosec     : %-7d",n, end-start);
    }

    private static void performGetOnMap(Map<Brommer, String> map, int n) {
        for (int i = 0; i< n; i++) {
            map.get(BrommerFactory.newFilledBrommer(
                    "Segway",
                    "IKBENEENBROMMERMETNUMMER" + i
                    , 9103,
                    20,
                    BrommerKlasse.A,
                    LocalDate.now().minusYears(50),
                    LocalDate.now().minusDays(20)
            ));
        }
    }

    public static void testSelectionSort() {
        for (int n = 1000; n < 3000; n += 1000) {
            Brommer.compareCounter = 0;
            List<Brommer> brommers = randomList(n);
            Kollections.selectionSort(brommers);

            System.out.println(n + ";" + Brommer.compareCounter);
        }

    }

    public static void testMergeSort() {
        for (int n = 1000; n < 3000; n += 1000) {
            Brommer.compareCounter = 0;
            List<Brommer> brommers = randomList(n);
            Kollections.mergeSort(brommers);

            System.out.println(n + ";" + Brommer.compareCounter);
        }
    }

    private static void fillMap(Map<Brommer, String> map, int n) {
        for (int i = 0; i < n; i++) {
            // model, chassisNummer, gewicht, aantalKeerOnderhoud, klasse, releaseDate, laatsteOnderhoud
            map.put(BrommerFactory.newFilledBrommer(
                    "Segway",
                    "IKBENEENBROMMERMETNUMMER" + i
                    , 9103,
                    20,
                    BrommerKlasse.A,
                    LocalDate.now().minusYears(50),
                    LocalDate.now().minusDays(20)
            ),"Ik ben de waarde "+i);
        }
    }
}
