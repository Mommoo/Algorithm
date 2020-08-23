package com.mommoo.baekjoon.no2920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final String ASCENDING = "ascending";
    private static final String DESCENDING = "descending";
    private static final String MIXED = "mixed";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        String type = null;
        int previous = Integer.parseInt(tokenizer.nextToken());
        while (tokenizer.hasMoreTokens()) {
            int num = Integer.parseInt(tokenizer.nextToken());
            if (previous + 1 == num && type == null) {
                type = ASCENDING;
            }

            if (previous - 1 == num && type == null) {
                type = DESCENDING;
            }

            if (Math.abs(num - previous) > 1 && type == null) {
                type = MIXED;
            }

            if ((ASCENDING.equals(type) && previous + 1 != num) || (DESCENDING.equals(type) && previous -1 != num)) {
                type = MIXED;
                break;
            }

            previous = num;
        }

        System.out.println(type);
    }
}
