package be.kdg.kollections.tree;

import be.kdg.kollections.Kollections;
import be.kdg.kollections.list.ArrayList;
import be.kdg.kollections.list.List;
import be.kdg.kollections.set.Set;

public class BinarySearchTree<T extends Comparable<T>> implements Set<T> {

    private Node<T> root;
    private int size;

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node<T> getNode(boolean leftNode) {
            return leftNode ? left : right;
        }
    }

    public BinarySearchTree() {
        this.root = new Node<>();
        size = 0;
    }

    @Override
    public void add(T element) {
        add(root, element);
    }

    private void add(Node<T> node, T element) {
        if(node.value == null) {
            node.value = element;
            node.left = new Node<>();
            node.right = new Node<>();
            size++;
        }else if(element.compareTo(node.value) < 0) {
            add(node.left, element);
        }else {
            add(node.right, element);
        }
    }

    @Override
    public boolean remove(T element) {
        return remove(root, element);
    }

    private boolean remove(Node<T> node, T element) {
        if(node.value == null) return false;

        if(node.value == element) {
            Node<T> lowestNode = node.right;
            if(lowestNode != null) {
                while (lowestNode.left != null) {
                    lowestNode = lowestNode.right;
                }
            }else {
                lowestNode = node.left;
            }


            node.value = lowestNode.value;
            lowestNode.value = null;
            size--;
            return true;
        }else if(element.compareTo(node.value) < 0) {
            return remove(node.left, element);
        }else {
            return remove(node.right, element);
        }
    }

    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node<T> node, T element) {
        if(node.value == null) return false;

        if(node.value == element) return true;

        if(element.compareTo(node.value) < 0) {
            return contains(node.left, element);
        }else {
            return contains(node.right, element);
        }
    }

    @Override
    public int indexOf(T element) {
        return Kollections.binarySearch(toList(), element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> toList() {
        ArrayList<T> list = new ArrayList<>();
        addNodeToList(list, root);
        return list;
    }

    private void addNodeToList(List<T> list, Node<T> node){
        if(node.left != null) {
            addNodeToList(list, node.left);
        }

        if(node.value != null) {
            list.add(node.value);
        }

        if(node.right != null) {
            addNodeToList(list, node.right);
        }

    }
}
