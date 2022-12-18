package be.kdg.threading;

import be.kdg.model.Brommer;
import be.kdg.model.BrommerFactory;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrommerRunnable implements Runnable{
    private Predicate<Brommer> predicate;
    private List<Brommer> brommerList;

    public BrommerRunnable(Predicate<Brommer> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void run() {
        brommerList = Stream.generate(BrommerFactory::newRandomBrommer).filter(predicate).limit(1000).collect(Collectors.toList());
    }

    public List<Brommer> getBrommerList() {
        return brommerList;
    }
}
