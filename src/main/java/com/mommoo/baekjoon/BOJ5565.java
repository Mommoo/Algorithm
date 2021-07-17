package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5565 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int totalPrice = Integer.parseInt(reader.readLine());
        for (int i = 0; i < 9; i++) {
            totalPrice -= Integer.parseInt(reader.readLine());
        }
        System.out.println(totalPrice);
    }
}
