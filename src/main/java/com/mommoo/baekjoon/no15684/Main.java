package com.mommoo.baekjoon.no15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][] ROW_LADDERS;
    private static int ROW_COUNT;
    private static int COL_COUNT;
    private static int MAX_BUILD_LADDER_COUNTS;

    public static void main(String[] args) throws Exception {
        init();

        int ladderCount = 0;
        int maxBuildLadderCount = Math.min(3, MAX_BUILD_LADDER_COUNTS);
        while (ladderCount <= maxBuildLadderCount) {
            if (isMatched(0, -1, ladderCount)) {
                System.out.println(ladderCount);
                return;
            }
            ladderCount++;
        }

        System.out.println(-1);
    }

    private static void init() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        COL_COUNT = Integer.parseInt(tokenizer.nextToken());
        int rowLadderCount = Integer.parseInt(tokenizer.nextToken());
        ROW_COUNT = Integer.parseInt(tokenizer.nextToken());

        ROW_LADDERS = new boolean[ROW_COUNT][COL_COUNT];
        while (rowLadderCount-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;

            ROW_LADDERS[row][col] = true;
        }

        MAX_BUILD_LADDER_COUNTS = countMaxBuildLadderCount();

        reader.close();
    }

    private static int countMaxBuildLadderCount() {
        int buildCount = 0;
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                if (canBuild(row, col)) {
                    buildCount++;
                }
            }
        }

        return buildCount;
    }

    private static boolean canBuild(int row, int col) {
        if (ROW_LADDERS[row][col]) {
            return false;
        }

        if (col > 0 && ROW_LADDERS[row][col - 1]) {
            return false;
        }

        if (col < COL_COUNT - 1 && ROW_LADDERS[row][col+1]) {
            return false;
        }

        return true;
    }

    private static boolean isMatched(int row, int col, int ladderCount) {
        if (ladderCount == 0) {
            return isAllSelfMatched();
        }

        int nextCol = col + 1;
        int nextRow = row;

        if (nextCol == COL_COUNT) {
            nextRow += 1;
            nextCol = 0;
        }

        if (nextRow == ROW_COUNT) {
            return false;
        }

        if (canBuild(nextRow, nextCol)) {
            ROW_LADDERS[nextRow][nextCol] = true;

            if (isMatched(nextRow, nextCol, ladderCount - 1)) {
                return true;
            }

            ROW_LADDERS[nextRow][nextCol] = false;
        }

        return isMatched(nextRow, nextCol, ladderCount);
    }

    private static boolean isAllSelfMatched() {
        for (int col = 0; col < COL_COUNT; col++) {
            if (!isSelfMatched(col)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isSelfMatched(int col) {
        int originCol = col;
        int beginRow = 0;
        while (beginRow < ROW_COUNT) {
            if (col < COL_COUNT - 1 && ROW_LADDERS[beginRow][col]) {
                col++;
            } else if (col > 0 && ROW_LADDERS[beginRow][col - 1]) {
                col--;
            }
            beginRow++;
        }

        return originCol == col;
    }
}
