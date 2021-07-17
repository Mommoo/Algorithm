package com.mommoo.baekjoon.no11052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] COST;
    private static int[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cardCount = Integer.parseInt(reader.readLine());
        COST = createCosts(cardCount, reader);
        DP = new int[cardCount + 1];

        for (int i = 1 ; i < COST.length; i++) {
            DP[i] = dfs(1, i, 0);
        }

        System.out.println(DP[cardCount]);
    }

    private static int[] createCosts(int size, BufferedReader reader) throws IOException {
        int[] costs = new int[size + 1];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i < costs.length; i++) {
            costs[i] = Integer.parseInt(tokenizer.nextToken());
        }

        return costs;
    }

    public static int dfs(int index, int remainCount, int costSum) {
        if (remainCount > 0 && DP[remainCount] != 0) {
            return DP[remainCount] + costSum;
        }

        if (remainCount == 0) {
            return costSum;
        } else if (remainCount < 0 || index >= COST.length) {
            return 0;
        }

        int maxCostSum = 0;
        maxCostSum = Math.max(maxCostSum, dfs(index, remainCount - index, costSum + COST[index]));
        maxCostSum = Math.max(maxCostSum, dfs(index + 1, remainCount, costSum));
        maxCostSum = Math.max(maxCostSum, dfs(index + 1, remainCount - index, costSum + COST[index]));
        return maxCostSum;
    }
}
