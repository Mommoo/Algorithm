package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2010 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        int enableSocketCount = 1;

        while (count-- > 0) {
            int multiTapSocketCount = Integer.parseInt(reader.readLine());
            enableSocketCount--;
            enableSocketCount += multiTapSocketCount;
        }

        System.out.println(enableSocketCount);
    }
}
