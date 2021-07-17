package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class BOJ6581 {
    private static final int MAX_SIZE = 80;

    public static void main(String[] args) throws Exception {
        StringBuilder builder = new StringBuilder();
        HTMLBuffer buffer = new HTMLBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = reader.readLine()) != null) {
            resolveNodes(line).forEach(node -> {
                switch(node.getType()) {
                    case BR:
                        builder.append(buffer.flushWithNewLine());
                        break;
                    case HR:
                        builder.append(resolveTextWhenNodeHR(buffer, node));
                        break;

                    default:
                        if (buffer.isOverflow(node.toString())) {
                            builder.append(buffer.flushWithNewLine());
                        }

                        buffer.write(node.toString());
                }
            });
        }

        if (buffer.hasElement()) {
            builder.append(buffer.flushWithNewLine());
        }

        System.out.print(builder);
    }

    private static List<Node> resolveNodes(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text);

        List<Node> nodes = new LinkedList<>();
        while (tokenizer.hasMoreTokens()) {
            Node node = new Node(tokenizer.nextToken());
            nodes.add(node);
        }

        return nodes;
    }

    private static String resolveTextWhenNodeHR(HTMLBuffer buffer, Node node) {
        StringBuilder builder = new StringBuilder();
        if (buffer.hasElement()) {
            builder.append(buffer.flushWithNewLine());
        }

        builder.append(node.toString())
               .append(System.lineSeparator());

        return builder.toString();
    }

    private enum NodeType {
        BR("<br>", System.lineSeparator()),
        HR("<hr>", createLine()),
        TEXT("", "");

        private final String nodeString;
        private final String value;

        private NodeType(String nodeString, String value) {
            this.nodeString = nodeString;
            this.value = value;
        }

        private static String createLine() {
            StringBuilder builder = new StringBuilder();
            int repeatCount = MAX_SIZE;
            while (repeatCount-- > 0) {
                builder.append("-");
            }
            return builder.toString();
        }

        public static NodeType of(String nodeString) {
            return Stream.of(values())
                         .filter(nodeType -> nodeType.nodeString.equals(nodeString))
                         .findFirst()
                         .orElse(TEXT);
        }

        public String getValue() {
            return value;
        }

        public boolean isTextNode() {
            return this == TEXT;
        }
    }

    private static class Node {
        private final String value;
        private final NodeType type;

        public Node(String value) {
            this.type = NodeType.of(value);
            this.value = type.isTextNode() ? value : type.value;
        }

        public NodeType getType() {
            return type;
        }

        public String toString() {
            return value;
        }
    }

    private static class HTMLBuffer {
        private StringBuilder builder = new StringBuilder();

        public boolean isOverflow(String text) {
            return builder.length() + 1 + text.length() > MAX_SIZE;
        }

        public boolean hasElement() {
            return builder.length() != 0;
        }

        public String flush() {
            String result = builder.toString();
            builder.setLength(0);
            return result;
        }

        public String flushWithNewLine() {
            return flush() + System.lineSeparator();
        }

        public void write(String text) {
            if (hasElement()) {
                builder.append(" ");
            }

            builder.append(text);
        }
    }
}
