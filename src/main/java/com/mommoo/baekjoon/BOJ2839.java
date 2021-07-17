package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2839 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalSugar = Integer.parseInt(br.readLine());

        int count = 0;

        while (totalSugar >= 3) {
            if (totalSugar % 5 == 0) {
                count += totalSugar / 5;
                totalSugar = 0;
            } else {
                count++;
                totalSugar -= 3;
            }
        }

        System.out.println(totalSugar == 0 ? count : -1);
    }
}
