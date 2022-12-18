package be.kdg.kollections.map;

import be.kdg.kollections.Collection;
import be.kdg.kollections.set.Set;

public interface Map<K, V> {
    void put(K key, V value);
    boolean remove(K key);
    V get(K key);
    Collection<V> values();
    Set<K> keySet();

    int size();
}
