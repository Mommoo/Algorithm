package com.mommoo.baekjoon.no7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FastMain {
    private static final int[] dx = {1, 0 , -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static final int COOKED_TOMATO = 1;
    private static final int UN_COOKED_TOMATO = 0;

    private static int MAX_ROW;
    private static int MAX_COL;
    private static int[][] TOMATOES;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        TOMATOES = new int[MAX_ROW][MAX_COL];

        TomatoProcessor tomatoProcessor = new TomatoProcessor();

        for (int row = 0; row < MAX_ROW; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < MAX_COL; col++) {
                TOMATOES[row][col] = Integer.parseInt(tokenizer.nextToken());
                if (TOMATOES[row][col] == COOKED_TOMATO) {
                    tomatoProcessor.addTomato(row, col);
                }
            }
        }

        reader.close();

        int days = 0;

        while (tomatoProcessor.hasMore()) {
            days += tomatoProcessor.process() ? 1 : 0;
        }

        System.out.println(checkIsAllDone() ? days : -1);
    }

    private static boolean checkIsAllDone() {
        for (int[] columns: TOMATOES) {
            for (int tomato: columns) {
                if (UN_COOKED_TOMATO == tomato) {
                    return false;
                }
            }
        }

        return true;
    }

    private static class RowAndCol {
        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class TomatoProcessor {
        private final Queue<RowAndCol> firstQueue = new LinkedList<>();
        private final Queue<RowAndCol> secondQueue = new LinkedList<>();

        public void addTomato(int row, int col) {
            firstQueue.offer(new RowAndCol(row, col));
        }

        public boolean hasMore() {
            return !firstQueue.isEmpty() || !secondQueue.isEmpty();
        }

        public boolean process() {
            boolean isProcessAtOnce = false;
            Queue<RowAndCol> currentQueue = firstQueue.isEmpty() ? secondQueue : firstQueue;
            Queue<RowAndCol> nextQueue = firstQueue.isEmpty() ? firstQueue : secondQueue;
            while (!currentQueue.isEmpty()) {
                RowAndCol rowAndCol = currentQueue.poll();
                int row = rowAndCol.row;
                int col = rowAndCol.col;

                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dy[i];
                    int nextCol = col + dx[i];
                    if (isValid(nextRow, nextCol) && TOMATOES[nextRow][nextCol] == UN_COOKED_TOMATO) {
                        TOMATOES[nextRow][nextCol] = COOKED_TOMATO;
                        nextQueue.offer(new RowAndCol(nextRow, nextCol));
                        isProcessAtOnce = true;
                    }
                }
            }

            return isProcessAtOnce;
        }

        private static boolean isValid(int row, int col) {
            return 0 <= row && row < MAX_ROW && 0 <= col && col < MAX_COL;
        }
    }
}
