package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5585 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int restMoney = 1000 - Integer.parseInt(reader.readLine());
        int[] coins = {500, 100, 50, 10, 5, 1};

        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            int coinCount = restMoney / coins[i];
            restMoney -= coinCount * coins[i];
            count += coinCount;
        }
        System.out.println(count);
    }
}
