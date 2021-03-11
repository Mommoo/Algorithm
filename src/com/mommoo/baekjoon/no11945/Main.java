package com.mommoo.baekjoon.no11945;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        while (N-- > 0) {
            String line = reader.readLine();
            for (int i = M - 1; i >= 0; i--) {
                BUILDER.append(line.charAt(i));
            }
            BUILDER.append('\n');
        }

        reader.close();
        System.out.println(BUILDER);
    }
}
