package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BOJ10845 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        Queue queue = new Queue();
        int count = Integer.parseInt(reader.readLine());
        while (count -- > 0) {
            String[] elements = reader.readLine().split(" ");
            QueueCommand queueCommand = QueueCommand.of(elements[0]);

            switch(queueCommand) {
                case PUSH:
                    queue.push(elements[1]);
                    break;
                case POP:
                    builder.append(queue.pop())
                           .append("\n");
                    break;
                case SIZE:
                    builder.append(queue.size())
                           .append("\n");
                    break;
                case EMPTY:
                    builder.append(queue.empty())
                           .append("\n");
                    break;
                case FRONT:
                    builder.append(queue.front())
                           .append("\n");
                    break;
                case BACK:
                    builder.append(queue.back())
                           .append("\n");
                    break;
            }
        }

        System.out.println(builder);
    }

    private enum QueueCommand {
        PUSH,
        POP,
        SIZE,
        EMPTY,
        FRONT,
        BACK;

        private static final Map<String, QueueCommand> stringCommandFinder
                = Stream.of(values())
                        .collect(Collectors.toMap(command -> command.name().toLowerCase(), Function.identity()));


        private static QueueCommand of(String stringCommand) {
            return stringCommandFinder.getOrDefault(stringCommand, null);
        }
    }

    private static class Queue {
        private QueueNode first;
        private QueueNode last;
        private int size = 0;

        public void push(String value) {
            QueueNode queueNode = new QueueNode(value);
            if (this.first == null) {
                this.first = queueNode;
            }

            if (this.last != null) {
                this.last.setNext(queueNode);
            }

            this.last = queueNode;
            this.size++;
        }

        public String pop() {
            if (this.first == null) {
                return "-1";
            }

            String value = this.first.value;
            this.first = this.first.next;
            if (this.first == null) {
                this.last = null;
            }
            this.size--;

            return value;
        }

        public int size() {
            return this.size;
        }

        public int empty() {
            return size() == 0 ? 1 : 0;
        }

        private String peekValue(QueueNode queueNode) {
            return Optional.ofNullable(queueNode)
                           .map(QueueNode::getValue)
                           .orElse("-1");
        }

        public String front() {
            return peekValue(this.first);
        }

        public String back() {
            return peekValue(this.last);
        }
    }

    private static class QueueNode {
        private final String value;
        private QueueNode next;

        public QueueNode(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public void setNext(QueueNode next) {
            this.next = next;
        }
    }
}
