package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1789 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long sum = Long.parseLong(reader.readLine());

        long beginNumber = 1;
        while (sum - beginNumber >= beginNumber + 1) {
            sum -= beginNumber;
            beginNumber++;
        }

        System.out.println(beginNumber);
    }
}
