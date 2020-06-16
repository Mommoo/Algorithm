package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ4889 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int orderNumber = 0;
        StringBuilder builder = new StringBuilder();
        while (true) {
            orderNumber++;

            String sentence = br.readLine();
            if (sentence.contains("-")) {
                break;
            }

            String invalidString = compileToRemoveStableString(sentence);
            int openCount = countOpenBracket(invalidString);
            int closeCount = invalidString.length() - openCount;
            int minChangeCount = (openCount / 2) + (closeCount / 2);
            if (openCount % 2 != 0) {
                minChangeCount += 2;
            }

            builder.append(orderNumber)
                   .append(". ")
                   .append(minChangeCount)
                   .append('\n');
        }

        System.out.println(builder);
    }

    private static String compileToRemoveStableString(String string) {
        Stack<Character> chars = new Stack<>();
        chars.push(string.charAt(0));

        for (int i = 1; i < string.length(); i++) {
            char c = string.charAt(i);
            if (!chars.isEmpty() && chars.peek() == '{' && c == '}') {
                chars.pop();
            } else {
                chars.push(c);
            }
        }

        return convertString(chars);
    }

    private static String convertString(Stack<Character> chars) {
        StringBuilder builder = new StringBuilder();
        while (!chars.isEmpty()) {
            builder.append(chars.pop());
        }

        return builder.reverse()
                      .toString();
    }

    private static int countOpenBracket(String string) {
        int count = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '{') {
                count++;
            }
        }

        return count;
    }
}
