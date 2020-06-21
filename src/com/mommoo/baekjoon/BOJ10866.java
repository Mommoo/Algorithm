package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10866 {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int commandCount = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();
        Deck deck = new Deck();
        while (commandCount -- > 0) {
            String commandString = br.readLine();
            Command command = Command.parse(commandString);
            switch (command) {
                case PUSH_FRONT:
                    int value = parseValue(commandString);
                    deck.pushFront(value);
                    break;
                case PUSH_BACK:
                    value = parseValue(commandString);
                    deck.pushBack(value);
                    break;
                case POP_FRONT:
                    builder.append(deck.popFront()).append('\n');
                    break;
                case POP_BACK:
                    builder.append(deck.popBack()).append('\n');
                    break;
                case SIZE:
                    builder.append(deck.size()).append('\n');
                    break;
                case EMPTY:
                    builder.append(deck.empty()).append('\n');
                    break;
                case FRONT:
                    builder.append(deck.front()).append('\n');
                    break;
                case BACK:
                    builder.append(deck.back()).append('\n');
                    break;
            }
        }

        br.close();
        System.out.println(builder);
    }

    public static int parseValue(String commandString) {
        BUILDER.setLength(0);
        for (int i = commandString.length() - 1; i >= 0; i--) {
            if (commandString.charAt(i) == ' ') {
                break;
            }

            BUILDER.append(commandString.charAt(i));
        }

        return Integer.parseInt(BUILDER.reverse().toString());
    }

    private static class Deck  {
        private Node firstNode;
        private Node lastNode;
        private int size = 0;

        public int size() {
            return size;
        }

        public int empty() {
            if (size == 0) {
                return 1;
            }

            return 0;
        }

        public int front() {
            if (firstNode == null) {
                return -1;
            }

            return firstNode.value;
        }

        public int back() {
            if (lastNode == null) {
                return -1;
            }

            return lastNode.value;
        }

        public void pushFront(int value) {
            size++;
            Node node = new Node(value);

            if (firstNode == null) {
                firstNode = lastNode = node;
                firstNode.setNextNode(lastNode);
                lastNode.setPreviousNode(firstNode);
                return;
            }

            node.setNextNode(firstNode);
            firstNode.setPreviousNode(node);
            firstNode = node;
        }

        public void pushBack(int value) {
            size++;
            Node node = new Node(value);

            if (lastNode == null) {
                firstNode = lastNode = node;
                firstNode.setNextNode(lastNode);
                lastNode.setPreviousNode(firstNode);
                return;
            }

            lastNode.setNextNode(node);
            node.setPreviousNode(lastNode);
            lastNode = node;
        }

        public int popFront() {
            if (firstNode == null) {
                return -1;
            }

            size--;
            int previousFirstNodeValue = firstNode.value;
            firstNode = firstNode.nextNode;
            if (firstNode != null) {
                firstNode.setPreviousNode(null);
            }

            if (size == 0) {
                firstNode = lastNode = null;
            }

            return previousFirstNodeValue;
        }

        public int popBack() {
            if (lastNode == null) {
                return -1;
            }

            size--;
            int previousLastNodeValue = lastNode.value;
            lastNode = lastNode.previousNode;
            if (lastNode != null) {
                lastNode.setNextNode(null);
            }

            if (size == 0) {
                firstNode = lastNode = null;
            }

            return previousLastNodeValue;
        }


    }

    private enum Command {
        PUSH_FRONT,
        PUSH_BACK,
        POP_FRONT,
        POP_BACK,
        SIZE,
        EMPTY,
        FRONT,
        BACK;

        public static Command parse(String commandLine) {
            for (Command command: values()) {
                if (commandLine.startsWith(command.name().toLowerCase())) {
                    return command;
                }
            }

            throw new RuntimeException();
        }
    }

    private static class Node {
        private final int value;
        private Node previousNode;
        private Node nextNode;

        public Node(int value) {
            this.value = value;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public void setPreviousNode(Node previousNode) {
            this.previousNode = previousNode;
        }
    }
}
