package com.mommoo.baekjoon.no2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int SUM;
    private static int[] COINS;
    private static int[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        SUM = Integer.parseInt(tokenizer.nextToken());
        COINS = createCoins(reader);

        DP = new int[SUM + 1];
        for (int i = 1 ; i < SUM + 1; i++) {
            DP[i] = dfs(i, 0);
        }

        System.out.println(DP[SUM]);
    }

    private static int[] createCoins(BufferedReader reader) throws IOException {
        COINS = new int[N];
        for (int i = 0 ; i < N; i++) {
            COINS[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(COINS);
        return COINS;
    }

    private static int dfs(int sum, int beginIndex) {
        if (sum == 0) {
            return 1;
        } else if (sum < 0) {
            return 0;
        }

        int totalCount = 0;

        for (int i = beginIndex; i < N; i++) {
            totalCount += dfs(sum - COINS[i], i);
        }

        return totalCount;
    }
}
