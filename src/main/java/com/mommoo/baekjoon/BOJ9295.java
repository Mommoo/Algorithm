package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9295 {
    private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i++) {
            append(i, br.readLine());
        }
        System.out.println(builder);
    }

    private static void append(int order, String numbers) {
        StringTokenizer tokenizer = new StringTokenizer(numbers);
        int sum = Integer.parseInt(tokenizer.nextToken()) + Integer.parseInt(tokenizer.nextToken());
        builder.append("Case ")
               .append(order)
               .append(": ")
               .append(sum)
               .append("\n");
    }
}
