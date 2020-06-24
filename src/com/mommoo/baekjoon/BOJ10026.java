package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ10026 {
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static char[][] COLOR_MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        COLOR_MAP = createColorMap(br);

        String result = countColorArea(false) + " " + countColorArea(true);

        System.out.println(result);
    }

    private static boolean isEqualWhenRenAndGreenMode(char c1, char c2) {
        return c1 == c2 || (c1 == 'R' && c2 == 'G') || (c1 == 'G' && c2 == 'R');
    }

    private static char[][] createColorMap(BufferedReader reader) throws IOException {
        int N = Integer.parseInt(reader.readLine());

        char[][] colorMap = new char[N][N];
        for (int row = 0; row < N; row++) {
            String mapLine = reader.readLine();
            for (int col = 0; col < N; col++) {
                colorMap[row][col] = mapLine.charAt(col);
            }
        }
        return colorMap;
    }

    private static int countColorArea(boolean mode) {
        Queue<RowAndCol> rowAndCols = new LinkedList<>();
        int count = 0;
        int N = COLOR_MAP.length;
        boolean[][] checks = new boolean[N][N];

        for (int row = 0 ; row < N; row++) {
            for (int col = 0 ; col < N; col++) {
                if (checks[row][col]) {
                    continue;
                }
                count++;

                rowAndCols.offer(new RowAndCol(row, col));
                checks[row][col] = true;

                while (!rowAndCols.isEmpty()) {
                    RowAndCol rowAndCol = rowAndCols.poll();
                    char currentColor = getColor(rowAndCol);
                    for (int i = 0; i < 4; i++) {
                        int nextRow = rowAndCol.row + dy[i];
                        int nextCol = rowAndCol.col + dx[i];
                        boolean isInvalid = !(0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N);

                        if (isInvalid || checks[nextRow][nextCol] || !(mode ? isEqualWhenRenAndGreenMode(currentColor, COLOR_MAP[nextRow][nextCol]) : currentColor == COLOR_MAP[nextRow][nextCol])) {
                            continue;
                        }

                        checks[nextRow][nextCol] = true;
                        rowAndCols.offer(new RowAndCol(nextRow, nextCol));
                    }
                }

            }
        }

        return count;
    }

    private static char getColor(RowAndCol rowAndCol) {
        return COLOR_MAP[rowAndCol.row][rowAndCol.col];
    }

    private static class RowAndCol {
        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
