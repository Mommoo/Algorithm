package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ10872 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        long answer = 1L;
        for (int i = 2; i <= number; i++) {
            answer *= i;
        }
        System.out.println(answer);
    }
}
