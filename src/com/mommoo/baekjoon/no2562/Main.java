package com.mommoo.baekjoon.no2562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int[] VISITS = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0 ; i < 9; i++) {
            int value = Integer.parseInt(reader.readLine());
            VISITS[value] = i + 1;
        }

        for (int i = 100; i > 0; i--) {
            if (VISITS[i] != 0) {
                System.out.println(i);
                System.out.println(VISITS[i]);
                return;
            }
        }
    }
}
