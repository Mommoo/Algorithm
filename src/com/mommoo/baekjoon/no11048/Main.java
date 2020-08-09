package com.mommoo.baekjoon.no11048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private final static int[] dx = {0, 1, 1};
    private final static int[] dy = {1, 0, 1};
    private static int MAX_ROW;
    private static int MAX_COL;
    private static int[][] MAP;
    private static int[][] VALUES;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());
        MAP = new int[MAX_ROW][MAX_COL];
        VALUES = new int[MAX_ROW][MAX_COL];

        for (int row = 0; row < MAX_ROW; row++) {
            StringTokenizer mapLine = new StringTokenizer(reader.readLine());
            for (int col = 0; col < MAX_COL; col++) {
                MAP[row][col] = Integer.parseInt(mapLine.nextToken());
            }
        }

        System.out.println(dfs(0,0,0));
    }

    public static int dfs (int row, int col, int candySum) {
        if (row >= MAX_ROW || col >= MAX_COL) {
            return -1;
        }

        if (row == MAX_ROW - 1 && col == MAX_COL - 1) {
            return candySum + MAP[row][col];
        }

        candySum += MAP[row][col];

        int maxCandySum = 0;
        for (int i = 0 ; i < 3; i++) {
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            maxCandySum = Math.max(dfs(nextRow, nextCol, candySum), maxCandySum);
        }

        return maxCandySum;
    }
}
