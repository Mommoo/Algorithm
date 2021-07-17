package com.mommoo.baekjoon.no2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = getInput();
        System.out.println(computeScore(input));
    }

    private static String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        reader.close();

        return input;
    }

    private static int computeScore(String input) {
        Deque<String> strings = new LinkedList<>();

        for (int i = 0 ; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(' || c == '[') {
                strings.push(Character.toString(c));
                continue;
            }

            if (strings.isEmpty()) {
                return 0;
            }

            String string = strings.pop();

            if (c == ')' && "(".equals(string)) {
                strings.push("2");
            } else if (c == ']' && "[".equals(string)) {
                strings.push("3");
            } else if (isNumber(string)) {

                int subScore = computeSubScore(strings, Integer.parseInt(string), c);

                if (subScore == -1) {
                    return 0;
                }

                strings.push(Integer.toString(subScore));
            } else {
                return 0;
            }
        }

        return computeTotalScore(strings);
    }

    private static int computeSubScore(Deque<String> strings, int number, char endTag) {
        if (strings.isEmpty()) {
            return -1;
        }

        String string = strings.pop();
        if (isNumber(string)) {
            int computedNumbers = number + Integer.parseInt(string);
            return computeSubScore(strings, computedNumbers, endTag);
        } else if ("[".equals(string) && endTag == ']') {
            return number * 3;
        } else if ("(".equals(string) && endTag == ')') {
            return number * 2;
        }

        return -1;
    }

    private static int computeTotalScore(Deque<String> strings) {
        int score = 0;

        while (!strings.isEmpty()) {

            if (!isNumber(strings.peek())) {
                return 0;
            }

            score += Integer.parseInt(strings.pop());
        }

        return score;
    }

    private static boolean isNumber(String string) {
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c > '9' || c < '0') {
                return false;
            }
        }

        return true;
    }
}
