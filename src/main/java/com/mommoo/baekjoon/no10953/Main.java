package com.mommoo.baekjoon.no10953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        while (count-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), ",");
            int sum = Integer.parseInt(tokenizer.nextToken()) + Integer.parseInt(tokenizer.nextToken());
            BUILDER.append(sum).append('\n');
        }

        System.out.println(BUILDER);
    }
}
