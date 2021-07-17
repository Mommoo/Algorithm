package com.mommoo.baekjoon.no2522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            appendLine(N, i, builder);
            builder.append('\n');
        }

        for (int i = N - 2; i >= 0; i--) {
            appendLine(N, i, builder);
            builder.append('\n');
        }

        System.out.println(builder);
    }

    private static void appendLine(int N, int index, StringBuilder builder) {
        int blank = N - index - 1;
        for (int k = 0; k < blank; k++) {
            builder.append(' ');
        }
        for (int k = 0; k < index + 1; k++) {
            builder.append('*');
        }
    }
}
