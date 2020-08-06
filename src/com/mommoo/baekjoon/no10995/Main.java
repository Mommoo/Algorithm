package com.mommoo.baekjoon.no10995;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int starCount = Integer.parseInt(reader.readLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < starCount; i++) {
            builder.append("* ");
        }

        String startLine = builder.toString();
        builder.setLength(0);
        for (int row = 0; row < starCount; row++) {
            builder.append(row % 2 == 0 ? "" :" ")
                   .append(startLine)
                   .append('\n');
        }

        System.out.println(builder);
    }
}
