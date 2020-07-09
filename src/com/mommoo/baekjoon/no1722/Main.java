package com.mommoo.baekjoon.no1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    private static final String FIND_NUMBER_GROUP = "1";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        if (FIND_NUMBER_GROUP.equals(tokenizer.nextToken())) {
            int order = Integer.parseInt(tokenizer.nextToken());
            List<NumberGroup> numberGroups = computeNumberGroups(N, order, null);
            System.out.println(numberGroups.get(numberGroups.size() - 1));
        } else {
            NumberGroup findNumberGroup = joiningToNumberGroup(N, tokenizer);
            List<NumberGroup> numberGroups = computeNumberGroups(N, -1, findNumberGroup);
            System.out.println(numberGroups.size());
        }
    }

    private static List<NumberGroup> computeNumberGroups(int N, int order, NumberGroup numberGroup) {
        List<NumberGroup> numberGroups = new ArrayList<>(N + 4);
        List<Integer> numbers = createNumbers(N);

        Deque<Node> deque = new LinkedList<>();
        deque.offer(Node.first(N));

        while (!deque.isEmpty()) {
            Node node = deque.poll();
            NumberGroup curNumberGroup = node.numberGroup;

            if (curNumberGroup.hasSize(N)) {
                numberGroups.add(curNumberGroup);
                if (numberGroups.size() == order || curNumberGroup.equals(numberGroup)) {
                    break;
                }
            }

            offerNextNodes(deque, node, numbers);
        }

        return numberGroups;
    }

    private static void offerNextNodes(Deque<Node> deque, Node curNode, List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (curNode.visits[i]) {
                continue;
            }

            Node nextNode = curNode.next(i, numbers.get(i));
            deque.offer(nextNode);
        }
    }

    private static List<Integer> createNumbers(int N) {
        List<Integer> numbers = new ArrayList<>(N + 4);
        for (int i = 0 ; i < N; i++) {
            numbers.add(i + 1);
        }
        return numbers;
    }

    private static NumberGroup joiningToNumberGroup(int count, StringTokenizer tokenizer) {
        NumberGroup numberGroup = NumberGroup.first();
        while (count -- > 0) {
            numberGroup.join(tokenizer.nextToken());
        }
        return numberGroup;
    }

    private static class NumberGroup {
        private final LinkedList<Integer> numbers;

        public NumberGroup(LinkedList<Integer> numbers) {
            this.numbers = numbers;
        }

        public static NumberGroup first() {
            return new NumberGroup(new LinkedList<>());
        }

        public NumberGroup next(int num) {
            LinkedList<Integer> newNumbers = new LinkedList<>(numbers);
            newNumbers.add(num);

            return new NumberGroup(newNumbers);
        }

        public void join(String stringNumber) {
            int num = Integer.parseInt(stringNumber);
            numbers.add(num);
        }

        public boolean hasSize(int size) {
            return numbers.size() == size;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            Integer firstNum = numbers.removeFirst();
            builder.append(firstNum);

            for (Integer num: numbers) {
                builder.append(" ")
                       .append(num);
            }

            numbers.addFirst(firstNum);

            return builder.toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null){
                return false;
            }
            return Objects.equals(other.toString(), this.toString());
        }
    }

    private static class Node {
        private final boolean[] visits;
        private final NumberGroup numberGroup;

        public Node(boolean[] visits, NumberGroup numberGroup) {
            this.visits = visits;
            this.numberGroup = numberGroup;
        }

        public static Node first(int N) {
            return new Node(new boolean[N], NumberGroup.first());
        }

        public Node next(int visitIndex, int number) {
            boolean[] nextVisits = new boolean[visits.length];
            System.arraycopy(visits, 0, nextVisits, 0, visits.length);
            nextVisits[visitIndex] = true;

            NumberGroup nextGroup = numberGroup.next(number);
            return new Node(nextVisits, nextGroup);
        }
    }
}
