package com.mommoo.baekjoon.no12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final char EXIST = '1';
    private static final char ABSENT = '0';

    private static String S;
    private static String T;

    public static void main(String[] args) throws IOException {
        setUp();
        StringBuilder builder = new StringBuilder(T);
        System.out.println(canChange(builder) ? EXIST : ABSENT);
    }

    private static void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        S = reader.readLine();
        T = reader.readLine();
        reader.close();
    }

    private static boolean equals(StringBuilder builder) {
        for (int i = 0 ; i < S.length(); i++) {
            if (builder.charAt(i) != S.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private static boolean canChange(StringBuilder next) {
        if (next.length() == S.length()) {
            return equals(next);
        }

        char last = next.charAt(next.length() - 1);
        next.setLength(next.length() - 1);
        if (last == 'A') {
            return canChange(next);
        }

        if (last == 'B') {
            next.reverse();
            return canChange(next);
        }

        return false;
    }
}
