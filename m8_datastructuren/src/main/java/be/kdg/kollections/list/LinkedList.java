package be.kdg.kollections.list;

import be.kdg.kollections.Kollections;

public class LinkedList<E> implements List<E> {

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    private Node<E> root;

    private int size;

    @Override
    public void add(E element) {
        if(root == null) {
            root = new Node<>(element);
        }else {
            Node<E> currentNode = root;
            while(currentNode.next != null) {
                currentNode = currentNode.next;
            }

            currentNode.next = new Node<>(element);
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        Node<E> newNode = new Node<>(element);
        newNode.next = getNode(index);

        if(index > 0) {
            replacePrevious(index, newNode);
        }else {
            root = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        Node<E> foundNode = getNode(index);
        assert foundNode != null;
        return foundNode.value;
    }

    private Node<E> getNode(int index) {
        if(index < 0 || index > size) {
            return null;
        }

        Node<E> currentNode = root;

        for(int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    @Override
    public void set(int index, E element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }

        Node<E> nodeToReplace = getNode(index);
        assert nodeToReplace != null;
        nodeToReplace.value = element;
    }

    private void replacePrevious(int index, Node<E> newNode) {
        Node<E> previousNode = getNode(index-1);
        assert previousNode != null;
        previousNode.next = newNode;
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

        Node<E> nodeToRemove = getNode(index);
        assert nodeToRemove != null;

        if(index == 0) {
            root = getNode(index+1);
        }else {
            replacePrevious(index, nodeToRemove.next);
        }
        size--;

        return nodeToRemove.value;
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
        return  indexOf(element) != -1;
    }

    @Override
    public int indexOf(E element) {
        return Kollections.linearSearch(this, element);
    }

}