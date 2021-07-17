package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ11047 {
    public static void main(String[] args) throws Exception {
        InputData inputData = new InputData();

        int coinValue = inputData.coinValue;
        LinkedList<Integer> coins = inputData.coins;

        int count = 0;

        while (coinValue != 0) {
            int nextMaxCoin = coins.pollLast();
            if (coinValue >= nextMaxCoin) {
                int canUseCount = coinValue / nextMaxCoin;
                coinValue -= nextMaxCoin * canUseCount;
                count += canUseCount;
            }
        }

        System.out.println(count);
    }

    private static class InputData {
        private final int coinValue;
        private final LinkedList<Integer> coins;

        public InputData() throws Exception {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

            int N = Integer.parseInt(tokenizer.nextToken());
            this.coinValue = Integer.parseInt(tokenizer.nextToken());
            this.coins = new LinkedList<>();

            while (N-- >0) {
                int coin = Integer.parseInt(bufferedReader.readLine());
                coins.add(coin);
            }
        }
    }
}
