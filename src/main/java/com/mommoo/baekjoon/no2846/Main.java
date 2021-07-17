package com.mommoo.baekjoon.no2846;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int startValue = Integer.parseInt(tokenizer.nextToken());
        int nextValue = startValue;
        int maxValue = 0;
        while (tokenizer.hasMoreTokens()) {
            int value = Integer.parseInt(tokenizer.nextToken());

            if (nextValue < value) {
                maxValue = Math.max(maxValue, value - startValue);
            } else {
                startValue = value;
            }

            nextValue = value;
        }

        System.out.println(maxValue);
    }
}
