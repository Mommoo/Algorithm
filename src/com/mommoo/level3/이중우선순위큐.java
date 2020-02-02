package com.mommoo.level3;

import java.util.Arrays;
import java.util.LinkedList;

public class 이중우선순위큐 {
    public static void main(String[] args) {
        String[] operations1 = {"I 16", "D 1"};
        String[] operations2 = {"I 7", "I 5", "I -5", "D -1"};
        System.out.println(Arrays.toString(new 이중우선순위큐().solution(operations1)));
        System.out.println(Arrays.toString(new 이중우선순위큐().solution(operations2)));
    }

    public int[] solution(String[] operations) {
        DoublePriorityQueue doublePriorityQueue = new DoublePriorityQueue();

        for (String o : operations) {
            Operation operation = Operation.parse(o);
            switch (operation.type) {
                case INSERT:
                    doublePriorityQueue.addNumber(operation.value);
                    break;
                case DELETE_HIGH_PRIORITY:
                    doublePriorityQueue.deleteHighPriorityElement();
                    break;
                case DELETE_LOW_PRIORITY:
                    doublePriorityQueue.deleteLowPriorityElement();
                    break;
            }
        }

        return doublePriorityQueue.getResult();
    }

    private static class Operation {
        private final Type type;
        private final Integer value;

        private Operation(Type type, Integer value) {
            this.type = type;
            this.value = value;
        }

        public static Operation parse(String operation) {
            String[] sub = operation.split(" ");
            Type type;
            switch (sub[0]) {
                case "I":
                    type = Type.INSERT;
                    break;

                case "D":
                    type = sub[1].equals("1") ? Type.DELETE_HIGH_PRIORITY : Type.DELETE_LOW_PRIORITY;
                    break;

                default:
                    throw new RuntimeException("invalid");
            }

            return new Operation(type, Integer.valueOf(sub[1]));
        }

        public enum Type {
            INSERT,
            DELETE_HIGH_PRIORITY,
            DELETE_LOW_PRIORITY;
        }
    }

    private static class DoublePriorityQueue {
        private final LinkedList<Integer> numbers = new LinkedList<>();

        public void addNumber(Integer number) {
            int index = findIndexBiggerThan(number);
            numbers.add(index, number);
        }

        public void deleteHighPriorityElement() {
            this.numbers.pollLast();
        }

        public void deleteLowPriorityElement() {
            this.numbers.pollFirst();
        }

        public int[] getResult() {
            if (this.numbers.isEmpty()) {
                return new int[]{0, 0};
            } else {
                return new int[]{this.numbers.getLast(), this.numbers.getFirst()};
            }
        }

        private int findIndexBiggerThan(Integer number) {
            int index = 0;
            for (long num : numbers) {
                if (num >= number) {
                    break;
                }

                index++;
            }

            return index;
        }
    }
}
