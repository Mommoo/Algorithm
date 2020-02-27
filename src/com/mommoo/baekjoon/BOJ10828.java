package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BOJ10828 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        Stack stack = new Stack();

        int count = Integer.parseInt(reader.readLine());
        while (count -- > 0) {
            String[] elements = reader.readLine().split(" ");
            StackCommand stackCommand = StackCommand.of(elements[0]);

            switch (stackCommand) {
                case PUSH:
                    stack.push(elements[1]);
                    break;
                case POP:
                    builder.append(stack.pop())
                           .append("\n");
                    break;
                case SIZE:
                    builder.append(stack.size())
                           .append("\n");
                    break;
                case EMPTY:
                    builder.append(stack.empty())
                           .append("\n");
                    break;
                case TOP:
                    builder.append(stack.top())
                           .append("\n");
                    break;
            }

        }
        reader.close();

        System.out.println(builder);
    }

    private enum StackCommand {
        PUSH("push"),
        POP("pop"),
        SIZE("size"),
        EMPTY("empty"),
        TOP("top");

        private static final Map<String, StackCommand> finder = Stream.of(values())
                                                                      .collect(Collectors.toMap(value -> value.stringCommand, Function.identity()));
        private final String stringCommand;

        private StackCommand(String stringCommand) {
            this.stringCommand = stringCommand;
        }

        private static StackCommand of(String stringCommand) {
            return finder.getOrDefault(stringCommand, null);
        }
    }

    private static class Stack {
        private static final int ELEMENT_EMPTY = 1;
        private String[] area = new String[10_000];
        private int currentIndex = -1;

        public void push(String x) {
            area[++currentIndex] = x;
        }

        public String pop() {
            if (empty() == ELEMENT_EMPTY) {
                return "-1";
            }

            return area[currentIndex--];
        }

        public String top() {
            if (empty() == ELEMENT_EMPTY) {
                return "-1";
            }

            return area[currentIndex];
        }

        public int size() {
            return currentIndex + 1;
        }

        public int empty() {
            return size() == 0 ? ELEMENT_EMPTY : 0;
        }
    }
}
