package com.mommoo.level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class 가장_먼_노드 {
    public static void main(String[] args) {
        System.out.println(new 가장_먼_노드().solution(6, new int[][]{
                new int[]{3, 6},
                new int[]{4, 3},
                new int[]{3, 2},
                new int[]{1, 3},
                new int[]{1, 2},
                new int[]{2, 4},
                new int[]{5, 2}
        }));
    }

    public int solution(int n, int[][] edges) {
        Nodes nodes = createNodes(edges);

        NodeMarker nodeMarker = new NodeMarker();

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new Node(1, 0));
        while (!nodeQueue.isEmpty()) {
            Node node = nodeQueue.poll();
            for (Integer nextNodeValue : nodes.getNextNodeValues(node.value)) {
                if (nodeMarker.isVisit(node.value, nextNodeValue)) {
                    continue;
                }

                nodeMarker.checkVisit(node.value, nextNodeValue);
                Node nextNode = node.nextNode(nextNodeValue);
                nodeQueue.offer(nextNode);
                nodeMarker.updateNodeCostIfMinimum(nextNode);
            }
        }

        return nodeMarker.countMaxNodeCost();
    }

    private static Nodes createNodes(int[][] edges) {
        Nodes nodes = new Nodes();

        for (int[] edge : edges) {
            nodes.addNode(edge);
        }

        return nodes;
    }

    private static class Nodes {
        private final Map<Integer, List<Integer>> nodeValues = new HashMap<>();

        public void addNode(int[] edge) {
            int node1 = edge[0];
            int node2 = edge[1];
            addNode(node1, node2);
            addNode(node2, node1);
        }

        private void addNode(int sourceNode, int endNode) {
            nodeValues.computeIfAbsent(sourceNode, node -> new ArrayList<>());
            nodeValues.get(sourceNode).add(endNode);
        }

        public List<Integer> getNextNodeValues(int sourceNode) {
            return Collections.unmodifiableList(nodeValues.get(sourceNode));
        }
    }

    private static class Node {
        private final int value;
        private final int cost;

        public Node(int value, int cost) {
            this.value = value;
            this.cost = cost;
        }

        public Node nextNode(int value) {
            return new Node(value, this.cost + 1);
        }
    }

    private static class NodeMarker {
        private final Set<String> visits = new HashSet<>();
        private final Map<Integer, Integer> nodeCost = new HashMap<>();

        private static String buildVisitKey(int sourceNode, int endNode) {
            return "s" + sourceNode + "e" + endNode;
        }

        public void updateNodeCostIfMinimum(Node node) {
            int previousCost = nodeCost.getOrDefault(node.value, Integer.MAX_VALUE);
            if (node.cost < previousCost) {
                nodeCost.put(node.value, node.cost);
            }
        }

        public void checkVisit(int sourceNode, int endNode) {
            visits.add(buildVisitKey(sourceNode, endNode));
            visits.add(buildVisitKey(endNode, sourceNode));
        }

        public boolean isVisit(int sourceNode, int endNode) {
            return visits.contains(buildVisitKey(sourceNode, endNode));
        }

        public int countMaxNodeCost() {
            int maxCost = findMaxCost();

            return (int) nodeCost.values()
                                 .stream()
                                 .filter(cost -> maxCost == cost)
                                 .count();
        }

        private int findMaxCost() {
            return nodeCost.values()
                           .stream()
                           .mapToInt(Integer::intValue)
                           .max()
                           .orElse(-1);
        }
    }
}
