
import be.kdg.kollections.ArrayList;
import be.kdg.kollections.Kollections;
import be.kdg.kollections.LinkedList;
import be.kdg.kollections.List;
import be.kdg.model.Brommer;
import be.kdg.model.BrommerFactory;

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
}
