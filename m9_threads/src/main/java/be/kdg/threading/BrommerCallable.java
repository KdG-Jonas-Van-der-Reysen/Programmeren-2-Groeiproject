package be.kdg.threading;

import be.kdg.model.Brommer;
import be.kdg.model.BrommerFactory;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrommerCallable implements Callable<List<Brommer>> {
    private Predicate<Brommer> predicate;

    public BrommerCallable(Predicate<Brommer> predicate) {
        this.predicate = predicate;
    }

    @Override
    public List<Brommer> call() throws Exception {
        return Stream.generate(BrommerFactory::newRandomBrommer).filter(predicate).limit(1000).collect(Collectors.toList());
    }
}
