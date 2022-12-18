package be.kdg.kollections.set;

import be.kdg.kollections.Collection;
import be.kdg.kollections.list.List;

public interface Set<E> extends Collection<E> {
    List<E> toList();
}
