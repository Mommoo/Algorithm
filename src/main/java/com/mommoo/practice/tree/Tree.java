package com.mommoo.practice.tree;

import java.util.List;

public interface Tree {
    Integer getHeight();
    Integer getHeight(int index);
    List<Integer> getNodes(int level);
    Integer getLeftNode(int index);
    Integer getRightNode(int index);
    Integer getSibling(int index);
    List<Integer> bfs(int beginIndex);
    List<Integer> preOrderTraversalByRecursive(int beginIndex);
    List<Integer> inOrderTraversalByRecursive(int beginIndex);
    List<Integer> postOrderTraversalByRecursive(int beginIndex);
    List<Integer> preOrderTraversalByIteration(int beginIndex);
    List<Integer> inOrderTraversalByIteration(int beginIndex);
    List<Integer> postOrderTraversalByIteration(int beginIndex);
}
