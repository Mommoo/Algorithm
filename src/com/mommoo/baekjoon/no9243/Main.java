package com.mommoo.baekjoon.no9243;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(reader.readLine());
        String previous = reader.readLine();
        String after = reader.readLine();
        reader.close();

        int len = previous.length();

        for (int i = 0; i < len; i++) {
            boolean pC;
            if (M % 2 == 0) {
                pC = previous.charAt(i) - '0' == 0;
            } else {
                pC = previous.charAt(i) - '0' == 1;
            }
            boolean aC = after.charAt(i) - '0' == 1;

            if (!(pC ^ aC)) {
                System.out.println("Deletion failed");
                return;
            }
        }

        System.out.println("Deletion succeeded");
    }
}
