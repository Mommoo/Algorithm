package com.mommoo.baekjoon.no10173;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            if ("EOI".equals(line)) {
                break;
            }

            BUILDER.append(containNemoIgnoreCase(line) ? "Found" : "Missing")
                   .append('\n');
        }

        System.out.println(BUILDER);
    }

    private static boolean containNemoIgnoreCase(String line) {
        for (int i = 0 ; i < line.length(); i++) {
            char c = Character.toLowerCase(line.charAt(i));
            if (i <= line.length() - 4 && c == 'n'
                && Character.toLowerCase(line.charAt(i + 1)) == 'e'
                && Character.toLowerCase(line.charAt(i + 2)) == 'm'
                && Character.toLowerCase(line.charAt(i + 3)) == 'o') {
                return true;
            }
        }

        return false;
    }
}
