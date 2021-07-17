package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1427 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String N = reader.readLine();

        int[] counter = new int[10];
        for (int i = 0; i < N.length(); i++) {
            int number = N.charAt(i) - '0';
            counter[number]++;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 9; i >= 0; i--) {
            int count = counter[i];
            while (count -- > 0) {
                builder.append(i);
            }
        }

        System.out.println(builder);
        reader.close();
    }
}
