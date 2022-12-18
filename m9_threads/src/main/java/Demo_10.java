import be.kdg.model.Brommer;
import be.kdg.model.BrommerFactory;
import be.kdg.model.BrommerKlasse;
import be.kdg.threading.BrommerAttacker;
import be.kdg.threading.BrommerRunnable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo_10 {
    public static void main(String[] args) throws InterruptedException {
        List<Brommer> brommerList = new ArrayList<>();
        Stream.generate(BrommerFactory::newRandomBrommer).limit(1000).forEach(brommerList::add);

        BrommerAttacker run1 = new BrommerAttacker(brommerList, b -> b.getKlasse().equals(BrommerKlasse.A));
        BrommerAttacker run2 = new BrommerAttacker(brommerList, b -> b.getReleaseDate().isAfter(LocalDate.of(2022, 1, 1)));
        BrommerAttacker run3 = new BrommerAttacker(brommerList, b -> b.getAantalKeerOnderhoud() > 5);

        Thread thread1 = new Thread(run1);
        Thread thread2 = new Thread(run2);
        Thread thread3 = new Thread(run3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("\n\nNa uitzuivering:");
        System.out.println("Brommers met klasse A                        : " + (int) brommerList.stream().filter(b -> b.getKlasse().equals(BrommerKlasse.A)).count());
        System.out.println("Brommers released na 1/1/2022                : " + (int) brommerList.stream().filter(b -> b.getReleaseDate().isAfter(LocalDate.of(2022, 1, 1))).count());
        System.out.println("Brommers die meer dan 5 keer onderhouden zijn: " + (int) brommerList.stream().filter(b -> b.getAantalKeerOnderhoud() > 5).count());
    }
}
