import be.kdg.model.Brommer;
import be.kdg.model.BrommerFactory;
import be.kdg.model.Brommers;

import java.util.stream.Stream;

public class Demo_12 {
    public static void main(String[] args) throws Exception {
        Brommers brommers = new Brommers(10000);
        Runnable r =  () -> Stream.generate(BrommerFactory::newRandomBrommer).limit(5000).forEach(brommers::add);

        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("\n\nAfter insertion by 2 threads, each adding 5000 objects: brommers = " + brommers.getSize());
    }
}
