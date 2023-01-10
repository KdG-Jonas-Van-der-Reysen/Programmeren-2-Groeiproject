import be.kdg.kollections.Kollections;
import be.kdg.kollections.List;
import be.kdg.model.Brommer;
import be.kdg.model.BrommerFactory;

import java.util.stream.Stream;

public class Demo_8 {
    public static void main(String[] args) {
        // 1. Test the randomlist function
        /*
        List<Brommer> brommers = PerformanceTester.randomList(30);

        // Print all elements
        printList(brommers)
        */


        // 2. Compare performance between ArrayList and LinkedList
        // PerformanceTester.compareArrayListAndLinkedList(20000);

        // 3. Test selectionsort
        /*System.out.println("Selectionsort\n===============");
        List<Brommer> brommers2 = PerformanceTester.randomList(10);
        System.out.println("Before sorting:");
        printList(brommers2);

        System.out.println("After sorting:");
        Kollections.selectionSort(brommers2);
        printList(brommers2);*/

        // 4. Test mergesort
        /*System.out.println("\n\nMergesort\n===============");
        List<Brommer> brommers3 = PerformanceTester.randomList(10);
        System.out.println("Before sorting:");
        printList(brommers3);

        System.out.println("After sorting:");
        Kollections.mergeSort(brommers3);
        printList(brommers3);*/

        // 5. Test compareCounters for both sorting strategies
        /*System.out.println("\n\nCompare counters\n===============");
        System.out.println("SelectionSort");
        PerformanceTester.testSelectionSort();

        System.out.println("MergeSort");
        PerformanceTester.testMergeSort();*/

        // 6. Test compareCouters for quickSort
        System.out.println("QuickSort");
        for (int n = 1000; n < 20000; n += 1000) {
            Brommer.compareCounter = 0;
            List<Brommer> brommers = PerformanceTester.randomList(n);
            Kollections.quickSort(brommers);

            System.out.println(n + ";" + Brommer.compareCounter);
        }

    }

    private static void printList(List<Brommer> brommers2) {
        for (int i = 0; i < brommers2.size(); i++) {
            System.out.println(brommers2.get(i));
        }
    }


}
