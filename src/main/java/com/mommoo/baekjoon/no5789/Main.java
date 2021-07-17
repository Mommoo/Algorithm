package com.mommoo.baekjoon.no5789;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final String DO_IT = "Do-it";
    private static final String DO_IT_NOT = "Do-it-Not";
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        while (N-- > 0) {
            String string = reader.readLine();
            int index = string.length() / 2;
            BUILDER.append(string.charAt(index - 1) == string.charAt(index) ? DO_IT : DO_IT_NOT)
                   .append('\n');
        }

        System.out.println(BUILDER);
    }
}
