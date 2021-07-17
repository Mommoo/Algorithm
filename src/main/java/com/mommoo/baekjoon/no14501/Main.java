package com.mommoo.baekjoon.no14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int DAY = 0;
    private static final int PAY = 1;

    private static int N;

    private static int[][] TABLES;

    public static void main(String[] args) throws IOException {
        setUp();

        int maxEarnMoney = 0;
        for (int i = 0 ; i < N; i++) {
            maxEarnMoney = Math.max(maxEarnMoney, findMaxEarnMoney(i));
        }

        System.out.println(maxEarnMoney);
    }

    // 분할정복 개념을 생각한다. 0일부터 최대값.. 1일부터 최대값.. N일부터 최대값.
    private static int findMaxEarnMoney(int beginIndex) {
        int usedDay = beginIndex + 1;

        // beginIndex 의 일을 못하는 경우
        if (usedDay + TABLES[beginIndex][DAY] > N + 1) {
            return 0;
        }

        // 일단 beginIndex 의 일은 할 수 있으니, 금액을 잡아준다.
        int maxEarnMoney = TABLES[beginIndex][PAY];

        for (int i = beginIndex + TABLES[beginIndex][DAY]; i < N; i++) {
            maxEarnMoney = Math.max(maxEarnMoney, TABLES[beginIndex][PAY] + findMaxEarnMoney(i));
        }

        return maxEarnMoney;
    }

    private static void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        TABLES = new int[N][2];
        for (int i = 0 ; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            TABLES[i][DAY] = Integer.parseInt(tokenizer.nextToken());
            TABLES[i][PAY] = Integer.parseInt(tokenizer.nextToken());
        }

        reader.close();
    }
}
