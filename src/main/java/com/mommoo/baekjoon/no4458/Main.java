package com.mommoo.baekjoon.no4458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());

        while (count-- > 0) {
            String string = reader.readLine();
            BUILDER.append(Character.toUpperCase(string.charAt(0)))
                   .append(string.substring(1))
                   .append('\n');
        }

        System.out.println(BUILDER);
    }
}
