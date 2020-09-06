package com.mommoo.baekjoon.no1463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());
        DP = new int[num + 1];
        DP[1] = 0;
        System.out.println(computeMinCount(num));
    }

    public static int computeMinCount(int num) {
        if (DP[num] != 0) {
            return DP[num];
        }

        if (num == 1) {
            return 0;
        }

        int minCount = Integer.MAX_VALUE;

        if (num % 3 == 0) {
            DP[num / 3] = computeMinCount(num / 3);
            minCount = Math.min(minCount, 1 + DP[num / 3]);
        }

        if (num % 2 == 0) {
            DP[num / 2] = computeMinCount(num / 2);
            minCount = Math.min(minCount, 1 + DP[num / 2]);
        }

        DP[num - 1] = computeMinCount( num - 1 );
        minCount = Math.min(minCount, 1 + DP[num - 1]);

        return DP[num] = minCount;
    }
}
