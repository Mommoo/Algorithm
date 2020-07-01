package com.mommoo.baekjoon.no10818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastMain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while (tokenizer.hasMoreTokens()) {
            int number = Integer.parseInt(tokenizer.nextToken());
            min = Math.min(min, number);
            max = Math.max(max, number);
        }

        System.out.println(min + " " + max);
    }
}
