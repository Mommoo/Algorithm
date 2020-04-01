package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2851 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int score = Integer.parseInt(reader.readLine());
            if (sum + score >= 100) {
                if (100 - sum < sum + score - 100) {
                    System.out.println(sum);
                } else {
                    System.out.println(sum + score);
                }
                return;
            }

            sum += score;
        }

        System.out.println(sum);
    }
}
