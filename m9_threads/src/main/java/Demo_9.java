import be.kdg.data.Data;
import be.kdg.model.*;
import be.kdg.threading.BrommerRunnable;

import java.time.LocalDate;
import java.util.*;

public class Demo_9 {
    private static int TESTCOUNT = 0;
    public static void main(String[] args) throws InterruptedException {
        List<Long> runTimes = new ArrayList<>();

        for (TESTCOUNT = 0; TESTCOUNT < 100; TESTCOUNT++) {
            BrommerRunnable run1 = new BrommerRunnable(b -> b.getKlasse().equals(BrommerKlasse.A));
            BrommerRunnable run2 = new BrommerRunnable(b -> b.getReleaseDate().isAfter(LocalDate.of(2022, 1, 1)));
            BrommerRunnable run3 = new BrommerRunnable(b -> b.getAantalKeerOnderhoud() > 5);

            Thread thread1 = new Thread(run1);
            Thread thread2 = new Thread(run2);
            Thread thread3 = new Thread(run3);

            long timeStart = System.currentTimeMillis();
            thread1.start();
            thread2.start();
            thread3.start();

            thread1.join();
            thread2.join();
            thread3.join();
            long timeEnd = System.currentTimeMillis();

            run1.getBrommerList().stream().limit(5).forEach(System.out::println);
            run2.getBrommerList().stream().limit(5).forEach(System.out::println);
            run3.getBrommerList().stream().limit(5).forEach(System.out::println);

            runTimes.add(timeEnd - timeStart);
        }

        System.out.printf("3 threads verzamelen elk 1000 brommers (gemiddelde uit 100 runs): %.2f ms",
                runTimes.stream().mapToDouble(Long::doubleValue).average().getAsDouble());

    }
}
