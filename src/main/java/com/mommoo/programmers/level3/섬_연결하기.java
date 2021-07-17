package com.mommoo.programmers.level3;

import java.util.*;

public class 섬_연결하기 {
    public static void main(String[] args) {
        int[][] costs = new int[][]{
                new int[] {0, 1, 1},
                new int[] {0, 2, 2},
                new int[] {1, 2, 5},
                new int[] {1, 3, 1},
                new int[] {2, 3, 8}
        };
        System.out.println(new 섬_연결하기().solution(4, costs));
    }

    public int solution(int n, int[][] costs) {
        PathFinder pathFinder = new PathFinder();
        pathFinder.addAll(costs);

        Set<Integer> nodeGroups = new HashSet<>();
        Integer beginNode = costs[0][0];
        nodeGroups.add(beginNode);

        List<Node> nodes = new LinkedList<>();

        while (nodeGroups.size() != n) {
            Node nextNode = pathFinder.findNextNode(nodeGroups);
            nodes.add(nextNode);
            nodeGroups.add(nextNode.value);
        }

        return nodes.stream()
                    .mapToInt(node -> node.edge)
                    .sum();
    }

    private static class PathFinder {
        private final Map<Integer, List<Node>> paths = new HashMap<>();

        public void addAll(int[][] costs) {
            for (int[] cost : costs) {
                add(cost);
            }
        }

        public void add(int[] cost) {
            int source = cost[0];
            int end = cost[1];
            int edge = cost[2];

            paths.computeIfAbsent(source, s -> new LinkedList<>())
                 .add(new Node(end, edge));

            paths.computeIfAbsent(end, e -> new LinkedList<>())
                 .add(new Node(source, edge));
        }

        public Node findNextNode(Set<Integer> sources) {

            return sources.stream()
                          .flatMap(source -> paths.get(source).stream())
                          .filter(node -> !sources.contains(node.value))
                          .min(Comparator.comparingInt(node -> node.edge))
                          .orElseThrow(RuntimeException::new);
        }
    }

    private static class Node {
        private final int value;
        private final int edge;

        public Node(int value, int edge) {
            this.value = value;
            this.edge = edge;
        }
    }
}
