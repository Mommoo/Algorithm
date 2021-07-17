package com.mommoo.baekjoon.no10991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int bufferLength = N * 2 - 1;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int beginIndex = N - i - 1;
            for (int k = 0; k < beginIndex; k++) {
                builder.append(' ');
            }

            boolean isPrinted = false;
            for (int j = beginIndex; j < bufferLength - beginIndex; j++) {
                if (!isPrinted) {
                    builder.append('*');
                    isPrinted = true;
                } else {
                    builder.append(' ');
                    isPrinted = false;
                }
            }

            builder.append('\n');
        }

        System.out.println(builder);
    }
}
