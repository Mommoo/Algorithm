package com.mommoo.baekjoon.no1259;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        main:while (true) {
            String number = reader.readLine();

            if ("0".equals(number)) {
                break;
            }

            int len = number.length();
            for (int i = len - 1; i >= 0; i--) {
                char c1 = number.charAt(i);
                char c2 = number.charAt(len - 1 - i);

                if (c1 != c2) {
                    BUILDER.append("no")
                           .append("\n");

                    continue main;
                }
            }

            BUILDER.append("yes")
                   .append("\n");
        }

        System.out.println(BUILDER);
    }
}
