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

        if (OPERATIONS.isEmpty()) {
            System.out.println(NUMS.get(0));
            return;
        }

        if (!OPERATIONS.contains('-')) {
            System.out.println(sumFrom(0));
            return;
        }

        int sum = 0;
        for (int i = 0; i < OPERATIONS.size(); i++) {
            sum += NUMS.get(i);

            if (OPERATIONS.get(i) == MINUS) {
                sum -= sumFrom(i + 1);
                break;
            }
        }

        System.out.println(sum);
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

    private static int sumFrom(int beginIndex) {
        int sum = 0;
        for (int i = beginIndex; i < NUMS.size(); i++) {
            sum += NUMS.get(i);
        }
        return sum;
    }
}
