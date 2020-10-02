package com.mommoo.baekjoon.no2741;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        for (int i = 0; i < number; i++) {
            BUILDER.append(i+1)
                   .append("\n");
        }
        System.out.println(BUILDER);
    }
}
