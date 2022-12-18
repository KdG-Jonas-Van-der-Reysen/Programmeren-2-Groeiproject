package be.kdg.kollections;

import be.kdg.kollections.list.ArrayList;
import be.kdg.kollections.list.LinkedList;
import be.kdg.kollections.list.List;

public class Kollections {

    private Kollections() { }

    public static <T extends Comparable<T>> void selectionSort(List<T> list) {
        for(int i = 0; i < list.size()-1; i++) {
            selectionSortWithStartIndex(list, i);
        }
    }

    private static <T extends Comparable<T>> void selectionSortWithStartIndex(List<T> list, int startIndex) {
        T smallest = null;
        int smallestIndex = -1;

        for(int i = startIndex; i < list.size(); i++) {
            T element = list.get(i);
            if(smallestIndex == -1 || element.compareTo(smallest) < 0) {
                smallest = element;
                smallestIndex = i;
            }
        }

        T first = list.get(startIndex);
        list.set(startIndex, smallest);
        list.set(smallestIndex, first);
    }


    public static <T extends Comparable<T>> void mergeSort(List<T> list) {
        if(list.size() <= 1) return;

        List<T> left = new LinkedList<>();
        List<T> right = new LinkedList<>();

        for(int i = 0; i < list.size(); i++) {
            if(i < list.size()/2)
                left.add(list.get(i));
            else
                right.add(list.get(i));
        }

        mergeSort(left);
        mergeSort(right);

        List<T> mergedList = merge(left, right);
        for(int i = 0; i < mergedList.size(); i++) {
            list.set(i, mergedList.get(i));
        }
    }

    private static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
        List<T> result = new ArrayList<>();

        while(left.size() > 0 && right.size() > 0) {
            if(left.get(0).compareTo(right.get(0)) < 0)
                result.add(left.remove(0));
            else
                result.add(right.remove(0));
        }

        while(left.size() > 0) {
            result.add(left.remove(0));
        }
        while(right.size() > 0) {
            result.add(right.remove(0));
        }

        return result;
    }

    public static <T extends Comparable<T>> void quickSort(List<T> list) {
        quickSort(list, 0, list.size()-1);
    }

    private static <T extends Comparable<T>> void quickSort(List<T> list, int start, int end) {
        if(start >= end || start < 0) return;

        int pivotIndex = partition(list, start, end);

        quickSort(list, start, pivotIndex-1);
        quickSort(list, pivotIndex+1, end);
    }

    private static <T extends Comparable<T>> int partition(List<T> list, int start, int end) {
        T pivot = list.get(end);

        int pivotIndex = start-1;

        for(int i = start; i < end-1; i++) {
            if(list.get(i).compareTo(pivot) <= 0) {
                pivotIndex++;
                T copyElement = list.get(pivotIndex);
                list.set(pivotIndex, list.get(i));
                list.set(i, copyElement);
            }
        }

        pivotIndex++;
        T copyElement = list.get(pivotIndex);
        list.set(pivotIndex, list.get(end));
        list.set(end, copyElement);

        return pivotIndex;
    }

    public static <T> int linearSearch(List<T> list, T element) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equals(element))
                return i;
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(List<T> sortedList, T element) {
        return binarySearch(sortedList, element, 0, sortedList.size()-1);
    }

    private static <T extends Comparable<T>> int binarySearch(List<T> sortedList, T element, int from, int to) {
        if(from < 0 || to > sortedList.size()-1) {
            return -1;
        }

        if(to-from <= 1) {
            if(element.compareTo(sortedList.get(from)) == 0)
                return from;
            else if (element.compareTo(sortedList.get(to)) == 0)
                return to;
            else
                return -1;  //list might not have been sorted or element was not present
        }

        int compareValue = element.compareTo(sortedList.get(from+to/2));

        if(compareValue == 0) {
            return from+to/2;
        }

        if(compareValue > 0) {
            return binarySearch(sortedList, element, (from+to/2)+1, to);
        }else {
            return binarySearch(sortedList, element, from, (from+to/2)-1);
        }
    }
}
