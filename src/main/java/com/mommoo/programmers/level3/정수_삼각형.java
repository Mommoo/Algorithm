package com.mommoo.programmers.level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 정수_삼각형 {
    public static void main(String[] args) {
        int[][] triangle = new int[][]{
                new int[]{7},
                new int[]{3, 8},
                new int[]{8, 1, 0},
                new int[]{2, 7, 4, 4},
                new int[]{4, 5, 2, 6, 5}
        };
        System.out.println(new 정수_삼각형().solution(triangle));
    }

    public int solution(int[][] triangle) {
        NodeLine nextNodeLine = NodeLine.first(triangle[0][0]);

        for (int i = 1; i < triangle.length; i++) {
            nextNodeLine = nextNodeLine.nextLine(triangle[i]);
        }

        return nextNodeLine.getMaxNodeValue();
    }

    private static class NodeLine {
        private final Map<Integer, Node> indexNodes;

        public NodeLine(Map<Integer, Node> indexNodes) {
            this.indexNodes = indexNodes;
        }

        public static NodeLine first(int value) {
            Node rootNode = Node.first(value);
            Map<Integer, Node> indexNodes = new HashMap<>();
            indexNodes.put(0, rootNode);
            return new NodeLine(indexNodes);
        }

        public NodeLine nextLine(int[] values) {
            Map<Integer, Node> indexNodes = new HashMap<>();

            for (Node node : this.indexNodes.values()) {
                for (Node nextNode : node.nextNode(values[node.index], values[node.index + 1])) {
                    Node currentNode = indexNodes.getOrDefault(nextNode.index, Node.EMPTY_NODE);
                    indexNodes.put(nextNode.index, getMaxNode(currentNode, nextNode));
                }
            }

            return new NodeLine(indexNodes);
        }

        /* 시간 초과 때문에... */
        public int getMaxNodeValue() {
            int max = -1;
            for (Node node : indexNodes.values()) {
                if (max < node.value) {
                    max = node.value;
                }
            }
            return max;
//            return indexNodes.values().stream()
//                             .mapToInt(node -> node.value)
//                             .max()
//                             .orElse(-1);
        }

        private Node getMaxNode(Node currentNode, Node nextNode) {
            if (nextNode.isBiggerThan(currentNode)) {
                return nextNode;
            }

            return currentNode;
        }
    }

    private static class Node {
        private static final Node EMPTY_NODE = new Node(-1, -1);
        private final int value;
        private final int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public static Node first(int value) {
            return new Node(value, 0);
        }

        public List<Node> nextNode(int leftValue, int rightValue) {
            Node left = new Node(value + leftValue, index);
            Node right = new Node(value + rightValue, index + 1);

            return Arrays.asList(left, right);
        }

        public boolean isBiggerThan(Node node) {
            return this.value > node.value;
        }
    }
}
