package com.mommoo.baekjoon.no4396;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main {
    private static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final char BOMB = '*';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        Set<RowAndCol> bombs = new HashSet<>();
        for (int row = 0 ; row < size; row ++) {
            String line = br.readLine();
            for (int col = 0 ; col < size; col++) {
                char c = line.charAt(col);
                if (c == BOMB) {
                    bombs.add(new RowAndCol(row, col));
                }
            }
        }

        boolean isBom = false;
        int[][] nums = new int[size][size];
        for (int row = 0; row < size; row++) {
            String line = br.readLine();
            for (int col = 0; col < size; col++) {
                char c = line.charAt(col);
                boolean isOpen = c == 'x';
                nums[row][col] = isOpen ? 0 : -1;

                if (isOpen && !isBom) {
                    isBom = bombs.contains(new RowAndCol(row, col));
                }
            }
        }

        for (RowAndCol bombRowAndCol: bombs) {
            for (int i = 0; i < dx.length; i++) {
                int nextRow = bombRowAndCol.row + dy[i];
                int nextCol = bombRowAndCol.col + dx[i];

                if (isValid(nextRow, nextCol, size, size) && nums[nextRow][nextCol] >= 0) {
                    nums[nextRow][nextCol]++;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int row = 0 ; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (isBom && bombs.contains(new RowAndCol(row, col))) {
                    builder.append('*');
                } else if (nums[row][col] >= 0) {
                    builder.append(nums[row][col]);
                } else {
                    builder.append('.');
                }
            }
            builder.append("\n");
        }

        System.out.println(builder);
    }

    private static boolean isValid(int row, int col, int maxRow, int maxCol) {
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }

    private static class RowAndCol {
        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof RowAndCol))
                return false;
            RowAndCol rowAndCol = (RowAndCol) o;
            return row == rowAndCol.row &&
                   col == rowAndCol.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
