package com.mommoo.baekjoon.no17127;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] NUMS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        NUMS = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0 ; i < N ; i++) {
            NUMS[i] = Integer.parseInt(tokenizer.nextToken());
        }

        System.out.println(dfs(1, 0, 0));
    }

    public static int dfs(int nextOrder, int nextIndex, int total) {
        if (nextOrder == 4) {
            return total + multiplyFrom(nextIndex);
        }

        int maxTotal = 0;
        for (int i = nextIndex; i < N - (4-nextOrder); i++) {
            int nextTotal = dfs(nextOrder + 1, i + 1, total + multiplyFromTo(nextIndex, i + 1));
            maxTotal = Math.max(maxTotal, nextTotal);
        }

        return maxTotal;
    }

    public static int multiplyFromTo(int from, int to) {
        int total = 1;
        for (int i = from; i < to; i++) {
            total *= NUMS[i];
        }
        return total;
    }

    public static int multiplyFrom(int from) {
        return multiplyFromTo(from, N);
    }
}
