package be.kdg.kollections.tree;

import be.kdg.kollections.list.ArrayList;
import be.kdg.kollections.list.List;

import java.util.function.Consumer;

public class Tree<V> {

    private TreeNode<V> root;
    private int size;

    public Tree() {
        size = 0;
    }

    public TreeNode<V> add(TreeNode<V> node, V value) {
        if(root == null) {
            root = new TreeNode<>(value, this);
            size++;
            return node;
        }

        if(node.rootTree != this) {
            throw new IllegalArgumentException("Node is not part of the tree");
        }

        TreeNode<V> newNode = node.add(value);
        size++;
        return newNode;
    }

    public TreeNode<V> get(V value) {
        return getWithRoot(value, root);
    }

    private TreeNode<V> getWithRoot(V value, TreeNode<V> root) {
        if(root == null) return null;

        if(root.value == value) {
            return root;
        }

        for(int i = 0; i < root.children.size(); i++) {
            TreeNode<V> foundNode = getWithRoot(value, root.children.get(i));
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }

    public void remove(V value) {
        removeWithParent(root, value, null);
    }

    private void removeWithParent(TreeNode<V> node, V value, TreeNode<V> parent) {
        if (node == null) return;

        if(node.value != value) {
            for (int i = 0; i < node.children.size(); i++) {
                removeWithParent(node.children.get(i), value, node);
            }
        }

        if(node.value == value) {
            if(parent == null) {
                root = node.children.get(0);
                for(int i = 1; i < node.children.size(); i++) {
                    root.add(node.children.get(i).value);
                }
            }else {
                for (int i = 0; i < node.children.size(); i++) {
                    parent.addNode(node.children.get(i));
                }
                parent.children.remove(node);
            }
            size--;
        }
    }

    public void traverseDepthFirst(Consumer<V> consumer) {
        traverseDepthFirst(consumer, root);
    }

    private void traverseDepthFirst(Consumer<V> consumer, TreeNode<V> node) {
        consumer.accept(node.value);
        List<TreeNode<V>> children = node.children;
        for (int i = 0; i < children.size(); i++) {
            traverseDepthFirst(consumer, children.get(i));
        }
    }

    public void traverseBreadthFirst(Consumer<V> consumer) {
        List<TreeNode<V>> queue = new ArrayList<>();
        queue.add(root);
        while (queue.size()>0) {
            TreeNode<V> current = queue.remove(0);
            consumer.accept(current.value);
            List<TreeNode<V>> children = current.children;
            for (int i=0;i<children.size();i++) {
                queue.add(children.get(i));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public TreeNode<V> getRoot() {
        return root;
    }
}
