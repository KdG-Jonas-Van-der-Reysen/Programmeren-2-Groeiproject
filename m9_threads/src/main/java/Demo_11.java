import be.kdg.model.Brommer;
import be.kdg.model.BrommerKlasse;
import be.kdg.threading.BrommerCallable;
import be.kdg.threading.BrommerRunnable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Demo_11 {
    private static int TESTCOUNT = 0;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Long> runTimes = new ArrayList<>();

        // Pool aanmaken
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (TESTCOUNT = 0; TESTCOUNT < 100; TESTCOUNT++) {
            BrommerCallable callable1 = new BrommerCallable(b -> b.getKlasse().equals(BrommerKlasse.A));
            BrommerCallable callable2 = new BrommerCallable(b -> b.getReleaseDate().isBefore(LocalDate.of(2022,1,1)));
            BrommerCallable callable3 = new BrommerCallable(b -> b.getAantalKeerOnderhoud() > 5);

            // List that'll keep our responses
            List<Future<List<Brommer>>> futureList = new ArrayList<>();

            // Add our "jobs" to the pool
            long timeStart = System.currentTimeMillis();
            futureList.add(pool.submit(callable1));
            futureList.add(pool.submit(callable2));
            futureList.add(pool.submit(callable3));

            // Wait for their values to be available
            for (var callableResult : futureList) {
                callableResult.get();
            }

            long timeEnd = System.currentTimeMillis();

            runTimes.add(timeEnd - timeStart);
        }

        System.out.printf("3 Futures verzamelen elk 1000 brommers (gemiddelde uit 100 runs): %.2f ms",
                runTimes.stream().mapToDouble(Long::doubleValue).average().getAsDouble());

        pool.shutdown();

    }
}
