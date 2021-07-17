package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16433 {
    private static int[] dx = {1, 1, -1 , -1};
    private static int[] dy = {1, -1, -1, 1};
    private static final char CARROT = 'v';
    private static final char EMPTY = '.';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(tokenizer.nextToken());
        int carrotRow = Integer.parseInt(tokenizer.nextToken()) - 1;
        int carrotCol = Integer.parseInt(tokenizer.nextToken()) - 1;

        char[][] carrotMap = createEmptyCarrotMap(size);

        Queue<RowAndCol> queue = new LinkedList<>();
        queue.offer(new RowAndCol(carrotRow, carrotCol));

        while (!queue.isEmpty()) {
            RowAndCol rowAndCol = queue.poll();
            plantCarrot(carrotMap, rowAndCol);

            for (int i = 0 ; i < 4 ; i++) {
                RowAndCol nextRowAndCol = rowAndCol.move(dx[i], dy[i]);
                if (nextRowAndCol.isValid(size, size) && canPlantBlock(carrotMap, nextRowAndCol)) {
                    queue.offer(nextRowAndCol);
                    plantCarrot(carrotMap, nextRowAndCol);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int row = 0 ; row < size; row ++) {
            for (int col = 0; col < size; col++) {
                builder.append(carrotMap[row][col]);
            }
            builder.append('\n');
        }

        System.out.println(builder);
    }

    private static char[][] createEmptyCarrotMap(int size) {
        char[][] carrotMap = new char[size][size];

        for (char[] mapLine: carrotMap) {
            Arrays.fill(mapLine, EMPTY);
        }

        return carrotMap;
    }

    private static void plantCarrot(char[][] carrotMap, RowAndCol rowAndCol) {
        int row = rowAndCol.row;
        int col = rowAndCol.col;
        carrotMap[row][col] = CARROT;
    }

    private static boolean canPlantBlock(char[][] carrotMap, RowAndCol rowAndCol) {
        int row = rowAndCol.row;
        int col = rowAndCol.col;
        return carrotMap[row][col] == EMPTY;
    }

    private static class RowAndCol {
        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public RowAndCol move(int additionalRow, int additionalCol) {
            return new RowAndCol(this.row + additionalRow, this.col + additionalCol);
        }

        public boolean isValid(int maxRow, int maxCol) {
            return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
        }
    }
}
