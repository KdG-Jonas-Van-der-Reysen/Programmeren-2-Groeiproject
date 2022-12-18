package be.kdg.kollections.list;


import be.kdg.kollections.Collection;

public interface List<E> extends Collection<E> {

    void add(E element);
    void add(int index, E element);

    E get(int index);
    //int indexOf(E element);

    void set(int index, E element);

    int size();

    E remove(int index);
}
