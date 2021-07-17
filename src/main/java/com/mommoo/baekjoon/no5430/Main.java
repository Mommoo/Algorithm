package com.mommoo.baekjoon.no5430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(reader.readLine());

        StringBuilder builder = new StringBuilder();
        while (TC-- > 0) {
            String function = reader.readLine();
            int size = Integer.parseInt(reader.readLine());
            NumberDeque deque = NumberDeque.of(size, reader.readLine());

            for (int i = 0 ; i < function.length(); i++) {
                char operation = function.charAt(i);
                if (operation == 'D') {
                    deque.pop();
                } else {
                    deque.reverse();
                }
            }

            builder.append(deque.toString())
                   .append("\n");
        }

        System.out.println(builder);
    }

    public static class NumberDeque {
        private final int[] values;
        private int backPointer;
        private int frontPointer;
        private boolean forward = true;
        private boolean isErrorInvoked = false;

        public NumberDeque(int[] values) {
            this.values = values;
            this.backPointer = values.length;
        }

        public static NumberDeque of(int size, String stringArray) {
            if (size == 0) {
                return empty();
            }
            String[] numberStrings = stringArray.substring(1, stringArray.length() - 1).split(",");
            int[] values = new int[numberStrings.length];
            int index = 0;
            for (String numberString : numberStrings) {
                values[index++] = Integer.parseInt(numberString);
            }
            return new NumberDeque(values);
        }

        public static NumberDeque empty() {
            return new NumberDeque(new int[0]);
        }

        public boolean pop() {
            if (isEmpty()) {
                isErrorInvoked = true;
                return false;
            }

            if (this.forward) {
                frontPointer++;
            } else {
                backPointer--;
            }

            return true;
        }

        public void reverse() {
            this.forward = !this.forward;
        }

        private String computeNumberStringIfForward() {
            StringBuilder builder = new StringBuilder();
            builder.append("[")
                   .append(values[frontPointer]);

            for (int i = frontPointer + 1; i < backPointer; i++) {
                builder.append(",")
                       .append(values[i]);
            }

            return builder.append("]")
                          .toString();
        }

        private boolean isEmpty() {
            return backPointer - frontPointer == 0;
        }

        private String computeNumberStringIfBackward() {
            StringBuilder builder = new StringBuilder();
            builder.append("[")
                   .append(values[backPointer - 1]);

            for (int i = backPointer - 2; i >= frontPointer; i--) {
                builder.append(",")
                       .append(values[i]);
            }

            return builder.append("]")
                          .toString();
        }

        @Override
        public String toString() {
            if (isErrorInvoked) {
                return "error";
            } else if (isEmpty()) {
                return "[]";
            }

            return this.forward ? computeNumberStringIfForward() : computeNumberStringIfBackward();
        }
    }
}
