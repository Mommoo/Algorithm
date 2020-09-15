package com.mommoo.baekjoon.no2711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCount = Integer.parseInt(reader.readLine());
        StringBuilder builder = new StringBuilder();
        while (testCount-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int index = Integer.parseInt(tokenizer.nextToken()) - 1;
            String word = tokenizer.nextToken();
            builder.setLength(0);
            for (int i = 0 ; i < word.length(); i++) {
                if (i == index) {
                    continue;
                }

                builder.append(word.charAt(i));
            }

            BUILDER.append(builder).append("\n");
        }

        System.out.println(BUILDER);
    }
}
