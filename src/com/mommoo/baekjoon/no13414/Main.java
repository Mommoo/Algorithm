package com.mommoo.baekjoon.no13414;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int count = Integer.parseInt(tokenizer.nextToken());
        int inputCount = Integer.parseInt(tokenizer.nextToken());

        Nodes nodes = new Nodes();

        while (inputCount-- > 0) {
            String input = reader.readLine();
            if (nodes.contain(input)) {
                nodes.remove(input);
            }

            nodes.add(input);
        }

        Iterator<String> iterator = nodes.createIterator();
        while (count -- > 0 && iterator.hasNext()) {
            write(iterator.next());
        }

        reader.close();
        System.out.println(BUILDER);
    }

    private static void write(String value) {
        BUILDER.append(value).append('\n');
    }

    private static class Nodes {
        private String first;
        private String last;
        private final Map<String, Node> values = new HashMap<>();

        public boolean contain(String value) {
            return values.containsKey(value);
        }

        public void remove(String value) {
            Node removeNode = values.remove(value);
            boolean isFirstNode = removeNode.pre == null;
            boolean isLastNode = removeNode.post == null;

            if (isFirstNode && isLastNode) {
                first = last = null;
            }

            if (!isFirstNode && isLastNode) {
                Node preNode = removeNode.pre;
                preNode.post = null;
                last = preNode.value;
            }

            if (isFirstNode && !isLastNode) {
                Node postNode = removeNode.post;
                postNode.pre = null;
                first = postNode.value;
            }

            if (!isFirstNode && !isLastNode) {
                Node preNode = removeNode.pre;
                Node postNode = removeNode.post;
                preNode.post = postNode;
                postNode.pre = preNode;
            }
        }

        public void add(String value) {
            Node node = new Node(value);
            if (first == null) {
                first = value;
                last = value;
            } else {
                Node lastNode = values.get(last);
                lastNode.post = node;
                node.pre = lastNode;
                last = value;
            }

            values.put(value, node);
        }

        public Iterator<String> createIterator() {
            return new Iterator<>() {
                private Node current = values.get(first);

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public String next() {
                    String value = current.value;
                    current = current.post;
                    return value;
                }
            };
        }
    }

    private static class Node {
        private Node pre;
        private Node post;
        private final String value;

        public Node(String value) {
            this.value = value;
        }
    }
}
