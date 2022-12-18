
import be.kdg.kollections.list.LinkedList;
import be.kdg.kollections.list.List;
import be.kdg.model.Brommer;
import be.kdg.model.BrommerFactory;

import java.util.ArrayList;
import java.util.Random;

public class PerformanceTester {

    //TODO: change this method for own use
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
        //TODO: test selectionsort for (int n = 1000; n < 20000; n += 1000)
    }

    public static void testMergeSort() {
        //TODO: test mergesort for (int n = 1000; n < 200000; n += 1000)
    }
}
