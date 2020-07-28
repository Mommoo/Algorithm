package com.mommoo.baekjoon.no1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] NUMBERS;
    private static int N;
    private static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        S = Integer.parseInt(tokenizer.nextToken());
        NUMBERS = new int[N];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            NUMBERS[i] = Integer.parseInt(tokenizer.nextToken());
        }

        System.out.println(dfs(0, S, false));
    }

    public static int dfs(int beginIndex, int remain, boolean count) {
        int result = 0;
        if (count && remain == 0) {
            result = 1;
        }

        if (beginIndex >= N) {
            return result;
        }

        result += dfs(beginIndex+1, remain - NUMBERS[beginIndex], true);
        result += dfs(beginIndex+1, remain, false);

        return result;
    }
}
