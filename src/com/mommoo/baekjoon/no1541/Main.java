package com.mommoo.baekjoon.no1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final List<Integer> NUMS = new ArrayList<>();
    private static final List<Character> OPERATIONS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String sentence = getInputSentence();
        parseSentence(sentence);
        System.out.println(dfs(NUMS, OPERATIONS));
    }

    private static String getInputSentence() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sentence = reader.readLine();
        reader.close();

        return sentence;
    }

    private static void parseSentence(String sentence) {
        int beginNumberIndex = 0;
        for (int i = 1 ; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == PLUS || c == MINUS) {
                Integer number = Integer.parseInt(sentence.substring(beginNumberIndex, i));
                NUMS.add(number);
                OPERATIONS.add(c);
                beginNumberIndex = i + 1;
            }
        }

        Integer number = Integer.parseInt(sentence.substring(beginNumberIndex));
        NUMS.add(number);
    }

    private static int dfs(List<Integer> remainNums, List<Character> remainOperations) {
        if (remainNums.size() == 1) {
            return remainNums.get(0);
        }

        int minResult = Integer.MAX_VALUE;
        for (int i = 0; i < remainNums.size() - 1 ; i++) {
            char operation = remainOperations.get(i);
            int targetNum = remainNums.get(i);
            int subNum = remainNums.get(i + 1);
            int nextResult = calculate(operation, targetNum, subNum);

            List<Integer> newRemainNums = new ArrayList<>(remainNums);
            newRemainNums.remove(i);
            newRemainNums.set(i, nextResult);

            List<Character> newRemainOperations = new ArrayList<>(remainOperations);
            newRemainOperations.remove(i);

            minResult = Math.min(minResult, dfs(newRemainNums, newRemainOperations));
        }

        return minResult;
    }

    private static int calculate(char operation, int num1, int num2) {
        switch(operation) {
            case PLUS: return num1 + num2;
            case MINUS: return num1 - num2;
            default: return -1;
        }
    }
}
