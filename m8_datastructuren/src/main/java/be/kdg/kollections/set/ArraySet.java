package be.kdg.kollections.set;

import be.kdg.kollections.Kollections;
import be.kdg.kollections.list.ArrayList;
import be.kdg.kollections.list.List;

public class ArraySet<T> implements Set<T> {

    private final ArrayList<T> elements;

    public ArraySet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        if(!contains(element))
            elements.add(element);
    }

    @Override
    public boolean remove(T element) {
        int elementIndex = elements.indexOf(element);

        if(elementIndex == -1)
            return false;

        elements.remove(elementIndex);
        return true;
    }

    @Override
    public boolean contains(T element) {
        return elements.contains(element);
    }

    @Override
    public int indexOf(T element) {
        return elements.indexOf(element);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public List<T> toList() {
        return elements;
    }
}
