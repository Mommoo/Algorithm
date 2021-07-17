package com.mommoo.programmers.level2;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 땅따먹기 {
    public static void main(String[] args) {
        int[][] land = {
                {1, 2, 3, 5},
                {5, 6, 7, 8},
                {4, 3, 2, 1}
        };

        System.out.println(new 땅따먹기().solution(land));
    }

    public int solution(int[][] land) {
        Nodes nextLineNodes = Nodes.firstLineNodes(land[0]);

        int maxLevel = land.length;
        for (int level = 1; level < maxLevel; level++) {
            int[] nextValues = land[level];
            nextLineNodes = nextLineNodes.nextLine(nextValues);
        }

        return nextLineNodes.getMaxTotalValueInNodes();
    }

    private static class Nodes {
        private final Node[] maxTopTwoNodes;
        private final List<Node> values;

        public Nodes(List<Node> values) {
            this.values = values;
            this.maxTopTwoNodes = createMaxTopTwoNodes(values);
        }

        private static Node[] createMaxTopTwoNodes(List<Node> values) {
            Node[] maxTopTwoNodes = new Node[2];

            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(values);
            maxTopTwoNodes[0] = priorityQueue.poll();
            maxTopTwoNodes[1] = priorityQueue.poll();

            return maxTopTwoNodes;
        }

        public static Nodes firstLineNodes(int[] values) {
            List<Node> nodes = IntStream.range(0, values.length)
                                        .mapToObj(idx -> new Node(idx, values[idx]))
                                        .collect(Collectors.toList());

            return new Nodes(nodes);
        }

        public Nodes nextLine(int[] values) {
            List<Node> nodes = IntStream.range(0, values.length)
                                        .mapToObj(idx -> findMaxNode(idx).nextNode(idx, values[idx]))
                                        .collect(Collectors.toList());

            return new Nodes(nodes);
        }

        public int getMaxTotalValueInNodes() {
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(values);
            return priorityQueue.poll().totalValue;
        }

        private Node findMaxNode(int currentIndex) {
            if (maxTopTwoNodes[0].index == currentIndex) {
                return maxTopTwoNodes[1];
            }

            return maxTopTwoNodes[0];
        }
    }

    private static class Node implements Comparable<Node>{
        private final int index;
        private final int totalValue;

        private Node(int index, int totalValue) {
            this.index = index;
            this.totalValue = totalValue;
        }

        public Node nextNode(int index, int nextValue) {
            int nextTotalValue = this.totalValue + nextValue;
            return new Node(index, nextTotalValue);
        }

        @Override
        public int compareTo(Node o) {
            return o.totalValue - this.totalValue;
        }
    }
}
