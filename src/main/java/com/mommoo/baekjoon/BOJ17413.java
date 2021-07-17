package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ17413 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sentence = reader.readLine();

        StringBuilder builder = new StringBuilder();
        Stack<Character> elements = new Stack<>();

        boolean isTagOpen = false;
        for (int i = 0; i < sentence.length(); i ++) {
            char c = sentence.charAt(i);
            if (c == '<') {
                appendReverseWord(elements, builder);
                isTagOpen = true;
            } else if (c == ' ' && !isTagOpen) {
                appendReverseWord(elements, builder);
                builder.append(' ');
                continue;
            }

            if (isTagOpen) {
                builder.append(c);
            } else {
                elements.push(c);
            }

            if (c == '>') {
                isTagOpen = false;
            }
        }

        appendReverseWord(elements, builder);

        System.out.println(builder);
    }

    private static void appendReverseWord(Stack<Character> elements, StringBuilder builder) {
        while (!elements.isEmpty()) {
            builder.append(elements.pop());
        }
    }
}
