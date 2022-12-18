package be.kdg.kollections.tree;

import be.kdg.kollections.list.ArrayList;
import be.kdg.kollections.list.List;

class TreeNode<V> {
    final List<TreeNode<V>> children;
    final V value;
    final Tree<V> rootTree;

    public TreeNode(V value, Tree<V> root) {
        this.value = value;
        this.rootTree = root;

        children = new ArrayList<>();
    }

    TreeNode<V> add(V value) {
        TreeNode<V> newNode = new TreeNode<>(value, rootTree);
        children.add(newNode);
        return newNode;
    }

    void addNode(TreeNode<V> node) {
        children.add(node);
    }
}
