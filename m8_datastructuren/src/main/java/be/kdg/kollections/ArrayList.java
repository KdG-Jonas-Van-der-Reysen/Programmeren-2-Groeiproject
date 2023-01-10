package be.kdg.kollections;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;
    private int currentCapacity;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
        currentCapacity = INITIAL_CAPACITY;
    }

    private void expand() {
        // Create a new array with double the capacity
        currentCapacity *= 2;
        Object[] newElements = new Object[currentCapacity];

        // Copy over the elements
        System.arraycopy(elements, 0, newElements, 0, elements.length);

        // Replace the old array with the new one
        elements = newElements;
    }

    @Override
    public void add(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

        // First, check if the index matches the array size (if the current capacity is 5, your last index will be 4).
        // In this case, we'll have to expand the array.
        if(size == currentCapacity) {
            expand();
        }

        if(index < size) {
            // If the index is smaller than the size, we'll have to shift all elements with that index and above to the right.
            // This is done by copying the elements from the index to the end of the array to the next index.
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = element;
        size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void set(int index, E element) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        elements[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        E oldValue = (E) elements[index];

        // Shift all elements with an index higher than the removed element to the left.
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;

        return oldValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

        return (E) elements[index];
    }
}
