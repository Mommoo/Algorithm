package com.mommoo.baekjoon.no14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int MAX_ROW;
    private static int MAX_COL;
    private static int[][] NUM_MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());
        NUM_MAP = createNumMap(reader);

        int max = - 1;
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                max = Math.max(max, computeRectangleMaxSum(row, col));
                max = Math.max(max, computeLongStickMaxNum(row, col));
                max = Math.max(max, computeGunMaxSum(row, col));
                max = Math.max(max, computeZigzagMaxSum(row, col));
                max = Math.max(max, computeTMaxSum(row, col));
            }
        }

        System.out.println(max);
    }

    private static int[][] createNumMap(BufferedReader reader) throws IOException {
        int[][] numMap = new int[MAX_ROW][MAX_COL];

        for (int row = 0; row < MAX_ROW; row++) {

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0 ; col < MAX_COL; col++) {
                numMap[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        return numMap;
    }

    private static int computeLongStickMaxNum(int row, int col) {
        int rowMode = -1;

        if (col + 4 <= MAX_COL) {
            rowMode = NUM_MAP[row][col] + NUM_MAP[row][col+1] + NUM_MAP[row][col+2] + NUM_MAP[row][col+3];
        }

        int colMode = -1;
        if (row + 4 <= MAX_ROW ) {
            colMode = NUM_MAP[row][col] + NUM_MAP[row+1][col] + NUM_MAP[row+2][col] + NUM_MAP[row+3][col];
        }

        return Math.max(rowMode, colMode);
    }

    private static int computeGunMaxSum(int row, int col) {
        int mode1 = -1;
        if (col + 2 <= MAX_COL && row + 3 <= MAX_ROW) {
            mode1 = NUM_MAP[row][col] + NUM_MAP[row+1][col] + NUM_MAP[row+2][col] + NUM_MAP[row+2][col+1];
            mode1 = Math.max(mode1, NUM_MAP[row][col] + NUM_MAP[row][col+1] + NUM_MAP[row + 1][col+1] + NUM_MAP[row+2][col+1]);
        }

        int mode2 = -1;
        if (col + 3 <= MAX_COL && row + 2 <= MAX_ROW) {
            mode2 = NUM_MAP[row+1][col] + NUM_MAP[row+1][col+1] + NUM_MAP[row+1][col+2] + NUM_MAP[row][col+2];
            mode2 = Math.max(mode2, NUM_MAP[row][col] + NUM_MAP[row][col+1] + NUM_MAP[row][col+2] + NUM_MAP[row+1][col]);
        }

        return Math.max(mode1, mode2);
    }

    private static int computeRectangleMaxSum(int row, int col) {
        if (row + 2 > MAX_ROW || col + 2 > MAX_COL) {
            return -1;
        }

        return NUM_MAP[row][col] + NUM_MAP[row + 1][col] + NUM_MAP[row][col + 1] + NUM_MAP[row + 1][col + 1];
    }

    private static int computeZigzagMaxSum(int row, int col) {
        int colMode = -1;
        int rowMode = -1;
        if (col + 3 <= MAX_COL && row + 2 <= MAX_ROW) {
            colMode = NUM_MAP[row][col+1] + NUM_MAP[row][col+2] + NUM_MAP[row+1][col] + NUM_MAP[row+1][col+1];
        }

        if (row + 3 <= MAX_ROW && col + 2 <= MAX_COL) {
            rowMode = NUM_MAP[row][col] + NUM_MAP[row+1][col] + NUM_MAP[row+1][col+1] + NUM_MAP[row+2][col+1];
        }

        return Math.max(colMode, rowMode);
    }

    private static int computeTMaxSum(int row, int col) {
        int rowMode = -1;
        if (row + 3 <= MAX_ROW && col + 2 <= MAX_COL) {
            rowMode = NUM_MAP[row][col] + NUM_MAP[row+1][col] + NUM_MAP[row+1][col+1] + NUM_MAP[row+2][col];
            rowMode = Math.max(rowMode, NUM_MAP[row][col+1] + NUM_MAP[row+1][col+1] + NUM_MAP[row+1][col] + NUM_MAP[row+2][col+1]);
        }

        int colMode = -1;
        if (col + 3 <= MAX_COL && row + 2 <= MAX_ROW) {
            colMode = NUM_MAP[row][col] + NUM_MAP[row][col+1] + NUM_MAP[row][col+2] + NUM_MAP[row+1][col+1];
            colMode = Math.max(colMode, NUM_MAP[row][col+1] + NUM_MAP[row+1][col] + NUM_MAP[row+1][col+1] + NUM_MAP[row+1][col+2]);
        }

        return Math.max(rowMode, colMode);
    }
}
