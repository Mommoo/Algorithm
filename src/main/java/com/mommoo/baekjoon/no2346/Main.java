package com.mommoo.baekjoon.no2346;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        LinkedList<Node> nodes = createNodes(reader);

        StringBuilder builder = new StringBuilder();
        builder.append("1");

        Node choiceNode = nodes.removeFirst();
        while (!nodes.isEmpty()) {
            int loopCount = Math.abs(choiceNode.value) % nodes.size();

            while (loopCount-- > 0) {
                if (choiceNode.value > 0) {
                    Node node = nodes.removeFirst();
                    nodes.offer(node);
                } else {
                    Node node = nodes.removeLast();
                    nodes.addFirst(node);
                }
            }

            choiceNode = choiceNode.value > 0 ? nodes.removeLast() : nodes.removeFirst();
            builder.append(" ")
                   .append(choiceNode.order);
        }

        System.out.println(builder);
    }

    private static LinkedList<Node> createNodes(BufferedReader reader) throws IOException {
        LinkedList<Node> nodes = new LinkedList<>();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        short index = 0;
        while (tokenizer.hasMoreTokens()) {
            nodes.offer(new Node(index++, Short.parseShort(tokenizer.nextToken())));
        }

        return nodes;
    }

    private static class Node {
        private final short order;
        private final short value;

        public Node(short index, short value) {
            this.order = ++index;
            this.value = value;
        }
    }
}
