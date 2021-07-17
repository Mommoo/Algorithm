package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1991 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Map<String, Node> nodes = new HashMap<>();
        while (N -- > 0) {
            putNode(reader.readLine(), nodes);
        }

        Node rootNode = nodes.get("A");
        System.out.println(preOrder(rootNode));
        System.out.println(inOrder(rootNode));
        System.out.println(postOrder(rootNode));
    }

    private static void putNode(String nodeLine, Map<String, Node> nodes) {
        StringTokenizer tokenizer = new StringTokenizer(nodeLine);
        String nodeValue = tokenizer.nextToken();
        String leftValue = tokenizer.nextToken();
        String rightValue = tokenizer.nextToken();

        nodes.putIfAbsent(nodeValue, new Node(nodeValue));
        Node node = nodes.get(nodeValue);
        node.setLeftNode(Node.of(leftValue));
        node.setRightNode(Node.of(rightValue));

        nodes.put(leftValue, node.leftNode);
        nodes.put(rightValue, node.rightNode);
    }

    private static String preOrder(Node node) {
        String result = node.value;

        Node leftNode = node.leftNode;
        if (leftNode != null) {
            result += preOrder(leftNode);
        }

        Node rightNode = node.rightNode;
        if (rightNode != null) {
            result += preOrder(rightNode);
        }

        return result;
    }

    private static String inOrder(Node node) {
        String result = "";

        Node leftNode = node.leftNode;
        if (leftNode != null) {
            result += inOrder(leftNode);
        }

        result += node.value;

        Node rightNode = node.rightNode;
        if (rightNode != null) {
            result += inOrder(rightNode);
        }

        return result;
    }

    private static String postOrder(Node node) {
        String result = "";

        Node leftNode = node.leftNode;
        if (leftNode != null) {
            result += postOrder(leftNode);
        }

        Node rightNode = node.rightNode;
        if (rightNode != null) {
            result += postOrder(rightNode);
        }

        result += node.value;

        return result;
    }

    private static class Node {
        private final String value;
        private Node leftNode;
        private Node rightNode;

        public Node(String value) {
            this.value = value;
        }

        public static Node of(String nodeValue) {
            if (".".equals(nodeValue)) {
                return null;
            }

            return new Node(nodeValue);
        }

        public void setLeftNode(Node node) {
            this.leftNode = node;
        }

        public void setRightNode(Node node) {
            this.rightNode = node;
        }

        @Override
        public String toString() {
            return "Node{" +
                   "value='" + value + '\'' +
                   '}';
        }
    }
}
