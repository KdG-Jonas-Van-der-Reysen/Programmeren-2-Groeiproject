package be.kdg.threading;

import be.kdg.model.Brommer;

import java.util.List;
import java.util.function.Predicate;

public class BrommerAttacker implements Runnable {
    private List<Brommer> brommerList;
    private Predicate<Brommer> predicate;
    private final Object lock = new Object();

    public BrommerAttacker(List<Brommer> brommerList, Predicate<Brommer> predicate) {
        this.brommerList = brommerList;
        this.predicate = predicate;
    }

    @Override
    public void run() {
        synchronized (brommerList) {
            brommerList.removeIf(predicate);
        }
    }
}
