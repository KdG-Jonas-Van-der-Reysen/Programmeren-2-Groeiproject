package be.kdg.kollections.list;

import be.kdg.kollections.Kollections;

public class ArrayList<E> implements List<E> {

    private static final int INITIAL_CAPACITY = 100;

    private E[] elements;
    private int size;
    private int currentCapacity;

    @SuppressWarnings({"unchecked"})
    public ArrayList(int initialCapacity) {
        currentCapacity = initialCapacity;
        elements = (E[]) new Object[currentCapacity];
        size = 0;
    }

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        if(size == currentCapacity-1) {
            expand();
        }

        if(index < size) {
            System.arraycopy(elements, index, elements, index+1, size-index);
        }

        elements[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        return elements[index];
    }

    @Override
    public void set(int index, E element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        elements[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        E oldValue = get(index);

        System.arraycopy(elements, index+1, elements, index, elements.length-index-1);
        size--;

        return oldValue;
    }

    @SuppressWarnings({"unchecked"})
    private void expand() {
        currentCapacity *= 2;
        E[] newElements = (E[]) new Object[currentCapacity];

        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    @Override
    public boolean remove(E element) {
        int elementIndex = indexOf(element);

        if(elementIndex == -1)
            return false;

        remove(elementIndex);
        return true;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(E element) {
        return Kollections.linearSearch(this, element);
    }
}
