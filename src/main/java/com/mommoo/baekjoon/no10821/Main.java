package com.mommoo.baekjoon.no10821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final char COMMA = ',';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numbers = br.readLine();
        br.close();

        int commaCount = 0;
        for (int i = 0 ; i < numbers.length(); i++) {
            char c = numbers.charAt(i);
            if (c == COMMA) {
                commaCount++;
            }
        }

        System.out.println(commaCount + 1);
    }
}
