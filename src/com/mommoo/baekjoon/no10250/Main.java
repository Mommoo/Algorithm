package com.mommoo.baekjoon.no10250;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(reader.readLine());
        while (TC-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Integer H = Integer.parseInt(tokenizer.nextToken());
            Integer W = Integer.parseInt(tokenizer.nextToken());
            Integer N = Integer.parseInt(tokenizer.nextToken());

            int width = N / H;
            int height = N % H;

            if (height == 0) {
                height++;
            } else {
                width++;
            }

            BUILDER.append(height).append(width < 10 ? "0" : "").append(width).append("\n");
        }

        System.out.println(BUILDER);
    }
}
