package com.mommoo.practice.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayBinaryTreeTest {
    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#provideGetHeight")
    void testGetHeight(int[] nodes, Integer height){
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.getHeight()).isEqualTo(height);
    }

    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#provideGetHeightAsArgument")
    void testGetHeightAsArgument(int[] nodes, int index, Integer height) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.getHeight(index)).isEqualTo(height);
    }

    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#provideGetNodes")
    void testGetNodes(int[] nodes, int level, List<Integer> findNodes) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.getNodes(level)).isEqualTo(findNodes);
    }

    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#provideGetLeftNode")
    void testGetLeftNode(int[] nodes, int index, Integer leftNode) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.getLeftNode(index)).isEqualTo(leftNode);
    }

    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#provideGetRightNode")
    void testGetRightNode(int[] nodes, int index, Integer rightNode) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.getRightNode(index)).isEqualTo(rightNode);
    }

    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#provideGetSibling")
    void testGetSibling(int[] nodes, int index, Integer siblingNode) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.getSibling(index)).isEqualTo(siblingNode);
    }

    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#provideBfs")
    void testBfs(int[] nodes, int index, List<Integer> traversalNodes) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.bfs(index)).isEqualTo(traversalNodes);
    }

    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#providePreOrderTraversal")
    void testPreOrderTraversal(int[] nodes, int index, List<Integer> preOrderTraversalNodes) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.preOrderTraversalByRecursive(index)).isEqualTo(preOrderTraversalNodes);
        assertThat(arrayBinaryTree.preOrderTraversalByIteration(index)).isEqualTo(preOrderTraversalNodes);
    }

    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#provideInOrderTraversal")
    void testInOrderTraversal(int[] nodes, int index, List<Integer> inOrderTraversalNodes) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.inOrderTraversalByRecursive(index)).isEqualTo(inOrderTraversalNodes);
        assertThat(arrayBinaryTree.inOrderTraversalByIteration(index)).isEqualTo(inOrderTraversalNodes);
    }

    @ParameterizedTest
    @MethodSource("com.mommoo.practice.tree.BinaryTreeArgumentsProvider#providePostOrderTraversal")
    void testPostOrderTraversal(int[] nodes, int index, List<Integer> postOrderTraversalNodes) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(nodes);
        assertThat(arrayBinaryTree.postOrderTraversalByRecursive(index)).isEqualTo(postOrderTraversalNodes);
        assertThat(arrayBinaryTree.postOrderTraversalByIteration(index)).isEqualTo(postOrderTraversalNodes);
    }
}