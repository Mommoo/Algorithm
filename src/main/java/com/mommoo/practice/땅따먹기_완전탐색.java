package com.mommoo.practice;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 땅따먹기_완전탐색 {
    public static void main(String[] args) {
        int[][] land = {
                {1, 2, 3, 5},
                {5, 6, 7, 8},
                {4, 3, 2, 1}
        };

        System.out.println(new 땅따먹기_완전탐색().solution(land));
    }

    public int solution(int[][] land) {
        int landMaxLevel = land.length - 1;

        Stack<Node> nodes = createStack(land[0]);

        List<Node> endNodes = new LinkedList<>();
        while (!nodes.isEmpty()) {
            Node node = nodes.pop();
            if (node.level == landMaxLevel) {
                endNodes.add(node);
                continue;
            }

            int nodeIndex = node.index;
            int nextLevel = node.level + 1;
            int[] nextLands = land[nextLevel];

            for (int i = 0; i < nextLands.length; i++) {
                if (i == nodeIndex) {
                    continue;
                }
                Node nextNode = node.next(i, nextLands[i]);
                nodes.push(nextNode);
            }
        }

        return endNodes.stream()
                       .mapToInt(Node::pathSum)
                       .max()
                       .orElse(-1);
    }

    private static Stack<Node> createStack(int[] firstLand) {
        Stack<Node> nodes = new Stack<>();

        for (int i = 0; i < firstLand.length; i++) {
            nodes.push(Node.first(i, firstLand[i]));
        }

        return nodes;
    }

    private static class Node {
        private final List<Node> paths;
        private final int index;
        private final int level;
        private final int value;

        public Node(int index, int level, int value, List<Node> paths) {
            this.index = index;
            this.level = level;
            this.value = value;
            this.paths = paths;
        }

        public static Node first(int index, int value) {
            return new Node(index, 0, value, Collections.emptyList());
        }

        public Node next(int index, int value) {
            List<Node> newPaths = new LinkedList<>(paths);
            newPaths.add(this);
            return new Node(index, level + 1, value, newPaths);
        }

        public int pathSum() {
            List<Node> pathNodes = new LinkedList<>(paths);
            pathNodes.add(this);
            return pathNodes.stream()
                            .mapToInt(n -> n.value)
                            .sum();

        }
    }

}
