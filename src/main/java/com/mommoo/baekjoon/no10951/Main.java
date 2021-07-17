package com.mommoo.baekjoon.no10951;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = reader.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(input);
            int num1 = Integer.parseInt(tokenizer.nextToken());
            int num2 = Integer.parseInt(tokenizer.nextToken());

            BUILDER.append(num1+num2).append("\n");
        }

        System.out.println(BUILDER);
    }
}
