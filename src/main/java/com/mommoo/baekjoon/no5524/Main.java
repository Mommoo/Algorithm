package com.mommoo.baekjoon.no5524;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int SUB = 'a' - 'A';
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());

        StringBuilder builder = new StringBuilder();
        while (count-- > 0) {
            builder.append(convertLowerCase(reader.readLine()))
                   .append('\n');
        }

        System.out.println(builder);
    }

    private static String convertLowerCase(String sentence) {
        BUILDER.setLength(0);

        for (int i = 0; i < sentence.length() ; i++) {
            char c = sentence.charAt(i);
            if ('A' <= c && c <= 'Z') {
                BUILDER.append((char)(c + SUB));
            } else {
                BUILDER.append(c);
            }
        }

        return BUILDER.toString();
    }
}
