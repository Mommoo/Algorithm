package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.function.BiPredicate;

public class BOJ10026 {
    private static char[][] COLOR_MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        COLOR_MAP = createColorMap(br);

        String result = countColorArea(Objects::equals) + " "
                        + countColorArea((c1, c2) -> isEqualWhenRenAndGreenMode(c1, c2));

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

    private static int countColorArea(BiPredicate<Character, Character> biPredicate) {
        Stack<RowAndCol> rowAndCols = new Stack<>();
        int count = 0;
        Set<RowAndCol> needToVisitRowAndCols = createNeedToVisitRowAndCols(COLOR_MAP.length);
        while (!needToVisitRowAndCols.isEmpty()) {
            count++;
            rowAndCols.push(needToVisitRowAndCols.iterator().next());
            while (!rowAndCols.isEmpty()) {
                RowAndCol rowAndCol = rowAndCols.pop();
                needToVisitRowAndCols.remove(rowAndCol);
                char currentColor = getColor(rowAndCol);

                RowAndCol[] nextRowAndCols = rowAndCol.nextRowAndCols();
                for (RowAndCol next : nextRowAndCols) {
                    if (needToVisitRowAndCols.contains(next) && biPredicate.test(currentColor, getColor(next))) {
                        rowAndCols.push(next);
                    }
                }
            }
        }

        return count;
    }

    private static Set<RowAndCol> createNeedToVisitRowAndCols(int N) {
        Set<RowAndCol> rowAndCols = new HashSet<>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                rowAndCols.add(new RowAndCol(row, col));
            }
        }
        return rowAndCols;
    }

    private static char getColor(RowAndCol rowAndCol) {
        return COLOR_MAP[rowAndCol.row][rowAndCol.col];
    }

    private static class RowAndCol {
        private final int[] dx = {1, 0, -1, 0};
        private final int[] dy = {0, 1, 0, -1};

        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public RowAndCol[] nextRowAndCols() {
            RowAndCol[] rowAndCols = new RowAndCol[4];

            for (int i = 0; i < 4; i++) {
                rowAndCols[i] = new RowAndCol(row + dy[i], col + dx[i]);
            }

            return rowAndCols;
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
