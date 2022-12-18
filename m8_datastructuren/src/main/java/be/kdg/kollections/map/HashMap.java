package be.kdg.kollections.map;

import be.kdg.kollections.Collection;
import be.kdg.kollections.list.ArrayList;
import be.kdg.kollections.list.List;
import be.kdg.kollections.set.ArraySet;
import be.kdg.kollections.set.Set;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 100;

    private static class Node<K, V> {
        final K key;
        final V value;

        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Node<K, V>[] buckets;
    private int size;
    private final int capacity;


    @SuppressWarnings({"unchecked"})
    public HashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        buckets = new Node[capacity];
    }

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void put(K key, V value) {
        int keyIndex = hash(key);

        Node<K, V> putNode = new Node<>(key, value);
        Node<K, V> rootNode = buckets[keyIndex];

        if(rootNode == null) {
            buckets[keyIndex] = putNode;
            size++;
            return;
        }

        while(rootNode.next != null) {
            rootNode = rootNode.next;
        }

        rootNode.next = putNode;
        size++;
    }

    @Override
    public boolean remove(K key) {
        int keyIndex = hash(key);

        Node<K, V> nodeToRemove = buckets[keyIndex];

        if(nodeToRemove == null) {
            return false;
        }

        if(nodeToRemove.key.equals(key)) {
            buckets[keyIndex] = nodeToRemove.next;
            size--;
            return true;
        }

        while(nodeToRemove.next != null && !nodeToRemove.next.key.equals(key)) {
            nodeToRemove = nodeToRemove.next;
        }

        if(nodeToRemove.next == null) {
            return false;
        }

        nodeToRemove.next = nodeToRemove.next.next;
        size--;
        return true;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()%capacity);
    }

    @Override
    public V get(K key) {
        int keyIndex = hash(key);

        Node<K, V> rootNode = buckets[keyIndex];

        while(rootNode != null) {
            if (rootNode.key.equals(key)) {
                return rootNode.value;
            }
            rootNode = rootNode.next;
        }

        return null;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>(size+1);
        for (Node<K, V> bucket : buckets) {
            Node<K, V> rootNode = bucket;
            while(rootNode != null) {
                values.add(rootNode.value);
                rootNode = rootNode.next;
            }
        }
        return values;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new ArraySet<>();
        for (Node<K, V> bucket : buckets) {
            Node<K, V> rootNode = bucket;
            while(rootNode != null) {
                keys.add(rootNode.key);
                rootNode = rootNode.next;
            }
        }
        return keys;
    }

    @Override
    public int size() {
        return size;
    }
}
