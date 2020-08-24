package com.mommoo.baekjoon.no2495;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0 ; i < 3; i++) {
            BUILDER.append(computeDuplicateCount(reader.readLine()))
                   .append("\n");
        }
        System.out.println(BUILDER);
    }

    public static int computeDuplicateCount(String string) {
        char previous = string.charAt(0);
        int maxCount = 1;
        int count = 1;

        for (int i = 1; i < string.length(); i++) {
            char current = string.charAt(i);
            if (previous == current) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 1;
            }

            previous = current;
        }

        return maxCount;
    }
}
