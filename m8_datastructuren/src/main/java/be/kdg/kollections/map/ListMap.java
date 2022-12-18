package be.kdg.kollections.map;

import be.kdg.kollections.Collection;
import be.kdg.kollections.list.ArrayList;
import be.kdg.kollections.list.List;
import be.kdg.kollections.set.ArraySet;
import be.kdg.kollections.set.Set;

public class ListMap<K, V> implements Map<K, V>{

    private final Set<K> keys;
    private final List<V> values;
    private int size;

    public ListMap() {
        keys = new ArraySet<>();
        values = new ArrayList<>();
    }

    @Override
    public void put(K key, V value) {
        keys.add(key);
        values.add(value);
        size++;
    }

    @Override
    public boolean remove(K key) {
        int elementIndex = keys.toList().indexOf(key);

        if(elementIndex == -1) {
                return false;
        }

        keys.remove(key);
        values.remove(elementIndex);

        size--;
        return true;
    }

    @Override
    public V get(K key) {
        int elementIndex = keys.toList().indexOf(key);

        if(elementIndex == -1)
            return null;

        return values.get(elementIndex);
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public int size() {
        return size;
    }
}
