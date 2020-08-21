package com.mommoo.baekjoon.no9086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(reader.readLine());

        while (TC -- > 0) {
            String string = reader.readLine();
            BUILDER.append(string.charAt(0))
                   .append(string.charAt(string.length() - 1))
                   .append("\n");
        }

        System.out.println(BUILDER);
    }
}
