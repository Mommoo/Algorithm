package com.mommoo.baekjoon.no1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    private static final String FIND_NUMBER_GROUP = "1";
    // 콤비네이션 찾는게 메모리가 많이 드는듯. 다시!!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        List<NumberGroup> numberGroups = computeOrderedNumberGroups(N);

        if (FIND_NUMBER_GROUP.equals(tokenizer.nextToken())) {
            int index = Integer.parseInt(tokenizer.nextToken()) - 1;
            NumberGroup findNumberGroup = numberGroups.get(index);
            System.out.println(findNumberGroup);
            return;
        }

        NumberGroup findNumberGroup = joiningToNumberGroup(N, tokenizer);
        int order = numberGroups.indexOf(findNumberGroup) + 1;
        System.out.println(order);
    }

    private static List<NumberGroup> computeOrderedNumberGroups(int N) {
        List<NumberGroup> numberGroups = new ArrayList<>();

        dfs(N, NumberGroup.first(), createNumbers(N), numberGroups);

        return numberGroups;
    }

    private static List<Integer> createNumbers(int N) {
        List<Integer> numbers = new ArrayList<>();
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

    private static void dfs(int N, NumberGroup numberGroup, List<Integer> remainNums, List<NumberGroup> results) {
        if (numberGroup.hasSize(N)) {
            results.add(numberGroup);
            return;
        }

        for (int i = 0; i < remainNums.size(); i++) {
            NumberGroup nextNumberGroup = numberGroup.next(remainNums.get(i));

            List<Integer> newRemainList = new ArrayList<>(remainNums);
            newRemainList.remove(i);

            dfs(N, nextNumberGroup, newRemainList, results);
        }
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
            return Objects.equals(other.toString(), this.toString());
        }
    }
}
