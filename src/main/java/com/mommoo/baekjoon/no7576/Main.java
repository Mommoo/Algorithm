package com.mommoo.baekjoon.no7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static final int UNDER_COOK_TOMATO = 0;
    private static final int ROTTEN_TOMATO = -1;
    private static final int COOK_TOMATO = 1;

    private static final int[] dx = {1, 0 , -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int maxCol = Integer.parseInt(tokenizer.nextToken());
        int maxRow = Integer.parseInt(tokenizer.nextToken());

        int[][] tomatoes = createTomatoes(br, maxRow, maxCol);

        TomatoCookTracker tomatoCookTracker = new TomatoCookTracker(tomatoes);
        while (tomatoCookTracker.hasMoreCook()) {
            tomatoCookTracker.cook();
        }

        System.out.println(tomatoCookTracker.getDays());
    }

    private static int[][] createTomatoes(BufferedReader br, int maxRow, int maxCol) throws IOException {
        int[][] tomatoes = new int[maxRow][maxCol];

        for (int row = 0; row < maxRow; row++) {
            StringTokenizer tomatoTokenizer = new StringTokenizer(br.readLine());
            int col = 0;
            while (tomatoTokenizer.hasMoreTokens()) {
                tomatoes[row][col++] = Integer.parseInt(tomatoTokenizer.nextToken());
            }
        }

        return tomatoes;
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

        public boolean isInValid(int maxRow, int maxCol) {
            return !(0 <= row && row < maxRow && 0 <= col && col < maxCol);
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
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
    }

    private static class TomatoCookTracker {
        private int days = 0;
        private final Set<RowAndCol> underCookTomatoes = new HashSet<>();
        private final Queue<RowAndCol> queue1 = new LinkedList<>();
        private final Queue<RowAndCol> queue2 = new LinkedList<>();
        private final int[][] tomatoes;

        private TomatoCookTracker(int[][] tomatoes) {
            this.tomatoes = tomatoes;
            checkTomatoes();
        }

        private void checkTomatoes() {
            for (int row = 0 ; row < tomatoes.length; row++) {
                for (int col = 0 ; col < tomatoes[0].length; col++) {
                    checkTomato(row, col);
                }
            }
        }

        private void checkTomato(int row, int col) {
            int tomato = tomatoes[row][col];
            if (UNDER_COOK_TOMATO == tomato) {
                underCookTomatoes.add(new RowAndCol(row, col));
                return;
            }

            if (COOK_TOMATO == tomato) {
                queue1.offer(new RowAndCol(row, col));
            }
        }

        public boolean hasMoreCook() {
            return !(queue1.isEmpty() && queue2.isEmpty()) && !underCookTomatoes.isEmpty();
        }

        private void cookTomato(RowAndCol rowAndCol) {
            tomatoes[rowAndCol.row][rowAndCol.col] = COOK_TOMATO;
        }

        private boolean doesNotCookTomato(RowAndCol rowAndCol) {
            int maxRow = tomatoes.length;
            int maxCol = tomatoes[0].length;

            if (rowAndCol.isInValid(maxRow, maxCol)) {
                return true;
            }

            int foundTomato = tomatoes[rowAndCol.row][rowAndCol.col];
            return (COOK_TOMATO == foundTomato) || (ROTTEN_TOMATO == foundTomato);
        }

        public void cook() {
            days++;
            Queue<RowAndCol> targetQueue = queue1.isEmpty() ? queue2 : queue1;
            Queue<RowAndCol> emptyQueue = queue1.isEmpty() ? queue1 : queue2;

            while (!targetQueue.isEmpty()) {
                RowAndCol rowAndCol = targetQueue.poll();
                for (int i = 0; i < 4; i++) {
                    RowAndCol nextRowAndCol = rowAndCol.move(dx[i], dy[i]);
                    if (doesNotCookTomato(nextRowAndCol)) {
                        continue;
                    }

                    emptyQueue.offer(nextRowAndCol);
                    cookTomato(nextRowAndCol);
                    underCookTomatoes.remove(nextRowAndCol);
                }
            }
        }

        public int getDays() {
            return underCookTomatoes.isEmpty() ? days : -1;
        }
    }
}
