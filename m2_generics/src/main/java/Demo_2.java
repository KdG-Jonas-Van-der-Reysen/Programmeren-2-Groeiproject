import be.kdg.data.Data;
import be.kdg.generics.PriorityQueue;
import be.kdg.model.Brommer;

import java.util.ArrayList;
import java.util.List;

public class Demo_2 {
    public static void main(String[] args) {
        // PriorityQueue with cities
        var myQueue = new PriorityQueue<>();
        myQueue.enqueue("Tokio", 2);
        myQueue.enqueue("Denver", 5);
        myQueue.enqueue("Rio", 2);
        myQueue.enqueue("Oslo", 3);
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(myQueue.toString());
        System.out.println("aantal: " + myQueue.getSize());
        System.out.println("positie van Tokio: " + myQueue.search("Tokio"));
        System.out.println("positie van Nairobi: " + myQueue.search("Nairobi"));
        for(var i = 0; i < 4; i++) {
            System.out.println("Dequeue: " + myQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + myQueue.getSize());

        // PriorityQueue with "brommers"
        var brommers = Data.getData();

        // Toevoegen aan de PriorityQueue
        var brommerQueue = new PriorityQueue<>();
        for (var brommer : brommers) {
            brommerQueue.enqueue(brommer, Data.generateRandom(1,6));
        }

        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(brommerQueue.toString());
        System.out.println("aantal: " + brommerQueue.getSize());
    }
}
