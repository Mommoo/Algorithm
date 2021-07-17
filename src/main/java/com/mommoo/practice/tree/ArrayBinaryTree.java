package com.mommoo.practice.tree;

import java.util.List;

public class ArrayBinaryTree implements Tree {
    private final int[] elements;

    private ArrayBinaryTree(int... elements) {
        this.elements = elements;
    }

    @Override
    public Integer getHeight() {
        return null;
    }

    @Override
    public Integer getHeight(int index) {
        return null;
    }

    @Override
    public List<Integer> getNodes(int level) {
        return null;
    }

    @Override
    public Integer getLeftNode(int index) {
        return null;
    }

    @Override
    public Integer getRightNode(int index) {
        return null;
    }

    @Override
    public Integer getSibling(int index) {
        return null;
    }

    @Override
    public List<Integer> bfs(int beginIndex) {
        return null;
    }

    @Override
    public List<Integer> preOrderTraversalByRecursive(int beginIndex) {
        return null;
    }

    @Override
    public List<Integer> inOrderTraversalByRecursive(int beginIndex) {
        return null;
    }

    @Override
    public List<Integer> postOrderTraversalByRecursive(int beginIndex) {
        return null;
    }

    @Override
    public List<Integer> preOrderTraversalByIteration(int beginIndex) {
        return null;
    }

    @Override
    public List<Integer> inOrderTraversalByIteration(int beginIndex) {
        return null;
    }

    @Override
    public List<Integer> postOrderTraversalByIteration(int beginIndex) {
        return null;
    }
}
