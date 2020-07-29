package com.mommoo.baekjoon.no11052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] COST;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cardCount = Integer.parseInt(reader.readLine());
        COST = new int[cardCount + 1];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i < COST.length; i++) {
            COST[i] = Integer.parseInt(tokenizer.nextToken());
        }
        System.out.println(dfs(1, cardCount, 0));
    }

    public static int dfs(int index, int remainCount, int costSum) {
        if (remainCount == 0) {
            return costSum;
        } else if (remainCount < 0 || index >= COST.length) {
            return 0;
        }

        int maxCostSum = 0;
        maxCostSum = Math.max(maxCostSum, dfs(index, remainCount - index, costSum + COST[index]));
        maxCostSum = Math.max(maxCostSum, dfs(index + 1, remainCount - (index + 1), costSum + COST[index]));
        maxCostSum = Math.max(maxCostSum, dfs(index + 1, remainCount, costSum));
        return maxCostSum;
    }
}
