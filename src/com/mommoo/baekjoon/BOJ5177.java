package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ5177 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= TC; testCase++) {
            String firstBlankNormalized = normalizeBlank(br.readLine());
            String secondBlankNormalized = normalizeBlank(br.readLine());

            if (doesNotEqualValues(firstBlankNormalized, secondBlankNormalized)) {
                printResult(testCase, false);
                continue;
            }

            printResult(testCase, true);
        }
    }

    public static String normalizeBlank(String string) {
        string = string.trim();

        Stack<Character> chars = new Stack<>();
        boolean isPreviousSpecialLetter = false;
        for (int i = 0 ; i < string.length(); i++) {
            char c = string.charAt(i);
            if (isSpecialLetter(c)) {
                isPreviousSpecialLetter = true;
                while (!chars.isEmpty() && chars.peek() == ' ') {
                    chars.pop();
                }
                chars.push(c);
                continue;
            }

            if (isPreviousSpecialLetter && c == ' ') {
                continue;
            }

            if (!isPreviousSpecialLetter && !chars.isEmpty() && chars.peek() == ' ') {
                while (!chars.isEmpty() && chars.peek() == ' ') {
                    chars.pop();
                }
                chars.push(' ');
            }
            isPreviousSpecialLetter = false;
            chars.push(c);
        }

        StringBuilder builder = new StringBuilder();
        while (!chars.isEmpty()) {
            builder.append(chars.pop());
        }

        return builder.reverse().toString();
    }

    public static boolean doesNotEqualValues(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return true;
        }

        for (int i = 0 ; i < s1.length(); i++) {
            char char1 = s1.charAt(i);
            char char2 = s2.charAt(i);
            if (!isEqualValues(char1, char2)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEqualValues(char c1, char c2) {
        return convertToComparableValue(c1) == convertToComparableValue(c2);
    }

    public static int convertToComparableValue(char c) {
        switch(c) {
            case '{': case '[': case '(':
                return 0;
            case '}': case ']': case ')':
                return 1;
            case ',': case ';':
                return 2;
            default:
                return Character.toLowerCase(c);
        }
    }

    public static boolean isSpecialLetter(char c) {
        return c == '{' || c == '}' || c =='[' || c == ']' || c == '(' || c ==')' || c == ',' || c == ';' || c == '.' || c == ':';
    }

    public static void printResult(int order, boolean result) {
        System.out.printf("Data Set %d: %s%n", order, result ? "equal" : "not equal");
        System.out.println();
    }
}
