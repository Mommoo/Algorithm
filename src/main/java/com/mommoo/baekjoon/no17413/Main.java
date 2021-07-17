package com.mommoo.baekjoon.no17413;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(reverseWords(getInput()));
    }

    private static String getInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();

        return input;
    }

    private static String reverseWords(String string) {
        StringBuilder builder = new StringBuilder();
        StringBuilder wordBuilder = new StringBuilder();
        boolean tag = false;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if ((c == ' ' && !tag) || c == '<') {
                addWord(builder, wordBuilder);
            }

            if (c == '<') {
                tag = true;
            }

            if (tag || c == ' ') {
                builder.append(c);
                if (c == '>') {
                    tag = false;
                }
                continue;
            }

            wordBuilder.append(c);
        }

        addWord(builder, wordBuilder);

        return builder.toString();
    }

    private static void addWord(StringBuilder builder, StringBuilder wordBuilder) {
        if (wordBuilder.length() == 0) {
            return;
        }
        builder.append(wordBuilder.reverse());
        wordBuilder.setLength(0);
    }
}
