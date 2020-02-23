package com.mommoo.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Practice {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        int[] arr = {111, 5, 3, 2, 6, 2, 444, 2116, 1, 3, 5, 6, 16, 33, 345, 15};
        long startTime = System.nanoTime();
        System.out.println(Arrays.stream(arr).max().orElse(-1));
        System.out.println(System.nanoTime() - startTime);
        System.out.println("----------------------");
        startTime = System.nanoTime();
        int max = - 1;
        for (int value : arr) {
            if (max < value) {
                max = value;
            }
        }
        System.out.println(max);
        System.out.println(System.nanoTime() - startTime);
        System.out.println("----------------------");
        startTime = System.nanoTime();
        PriorityQueue<Integer> queue = Arrays.stream(arr).boxed().collect(Collectors.toCollection(() -> new PriorityQueue<>()));
        System.out.println(queue.poll());
        System.out.println(System.nanoTime() - startTime);
    }

    private static class Node {
        private final int index;
        private final int level;
        private final int value;
        private final List<Node> nodePaths;

        public Node(int index, int level, int value, List<Node> nodePaths) {
            this.index = index;
            this.level = level;
            this.value = value;
            this.nodePaths = nodePaths;
        }

        private static Node first(int index, int value) {
            return new Node(index, 0, value, new LinkedList<>());
        }

        private Node nextNode(int nextIndex, int nextValue) {
            List<Node> newNodePaths = new LinkedList<>(this.nodePaths);
            newNodePaths.add(this);
            return new Node(nextIndex, level + 1, nextValue, newNodePaths);
        }

        public String getPathString() {
            List<Node> newNodePaths = new LinkedList<>(this.nodePaths);
            newNodePaths.add(this);
            return newNodePaths.stream()
                               .map(n -> n.value)
                               .map(String::valueOf)
                               .collect(Collectors.joining(" -> "));
        }
    }
}
