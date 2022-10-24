package be.kdg.generics;

import be.kdg.model.Brommer;

import java.util.*;

public class PriorityQueue<T> implements FIFOQueue<T>{
    // Declare treemap with comparator
    private TreeMap<Integer, LinkedList<T>> queue = new TreeMap<>(Comparator.reverseOrder());

    @Override
    public boolean enqueue(T element, int priority) {
        // Check if the element is already in the queue
        if (search(element) != -1) {
            // Element is already in the queue, so return false!
            return false;
        }

        // Check if there's an entry already for this priority
        if (queue.containsKey(priority)) {
            // There's already an entry for this priority, so add the element to the list
            queue.get(priority).add(element);
        } else {
            // There's no entry for this priority, so create a new list and add the element
            LinkedList<T> list = new LinkedList<>();
            list.add(element);
            queue.put(priority, list);
        }
        return false;
    }

    @Override
    public T dequeue() {
        int highestPriority = queue.firstKey();
        List<T> list = queue.get(highestPriority);

        // Check if the list is null or empty (in that case the queue is empty)
        if (list == null || list.isEmpty()) {
            // List is null or empty, so return null
            queue.remove(highestPriority);
            return null;
        }

        // Get the first element from the list
        T element = list.get(0);

        // Check if the element is not null
        if (element != null) {
            // Element is not null, so remove it from the list
            queue.firstEntry().getValue().removeFirst();

            // If the list is now empty, remove the entry from the queue
            if (queue.firstEntry().getValue().isEmpty()) {
                queue.remove(queue.firstEntry().getKey());
            }
            return element;
        } else {
            return null;
        }
    }

    @Override
    public int search(T element) {
        // Find the element in the queue and return the position in the queue
        int position = 0;
        for (Map.Entry<Integer, LinkedList<T>> entry : queue.entrySet()) {
            for (T t : entry.getValue()) {
                if (t.equals(element)) {
                    return position;
                }
                position++;
            }
        }
        return -1;
    }

    @Override
    public int getSize() {
        // Return the total number of elements in the queue
        int elementsInQueue = 0;
        for (Map.Entry<Integer, LinkedList<T>> entry : queue.entrySet()) {
            elementsInQueue += entry.getValue().size();
        }

        return elementsInQueue;
    }

    @Override
    public String toString() {
        // Return a string representation of the queue that shows the priority and the element
        StringBuilder sb = new StringBuilder();

        // Loop over the elements in the queue
        for (Map.Entry<Integer, LinkedList<T>> entry : queue.entrySet()) {

            // Get the list from this priority
            for (T t : entry.getValue()) {
                // entry.getKey() is the priority
                sb.append(entry.getKey()).append(": ").append(t).append("\n");
            }
        }

        return sb.toString();
    }
}
