package com.mommoo.baekjoon.no15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {1, 0, -1, 0};
    private static final Queue<RowAndCol> QUEUE = new LinkedList<>();
    private static boolean[][] VISITS;

    private static final char EMPTY = '0';
    private static final char HOUSE = '1';
    private static final char CHICKEN = '2';
    private static final List<RowAndCol> CHICKENS = new ArrayList<>();

    private static int SIZE;
    private static int CHICKEN_COUNT;
    private static char[][] MAP;
    private static List<Set<RowAndCol>> CHICKEN_LOCATION_CONDITIONS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        SIZE = Integer.parseInt(tokenizer.nextToken());
        CHICKEN_COUNT = Integer.parseInt(tokenizer.nextToken());
        MAP = createMapAndFillChickens(reader);
        VISITS = new boolean[SIZE][SIZE];
        CHICKEN_LOCATION_CONDITIONS = createChickenLocationConditions();

        int minDistanceSum = Integer.MAX_VALUE;
        for (Set<RowAndCol> chickenLocations: CHICKEN_LOCATION_CONDITIONS) {
            minDistanceSum = Math.min(minDistanceSum, computeMinimumChickenDistanceSum(chickenLocations));
        }

        System.out.println(minDistanceSum);
    }

    private static char[][] createMapAndFillChickens(BufferedReader reader) throws IOException {
        char[][] map = new char[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < SIZE; col++) {
                map[row][col] = tokenizer.nextToken().charAt(0);
                if (map[row][col] == CHICKEN) {
                    CHICKENS.add(new RowAndCol(row, col));
                }
            }
        }
        return map;
    }

    private static List<Set<RowAndCol>> createChickenLocationConditions() {
        List<Set<RowAndCol>> chickenLocationConditions = new LinkedList<>();
        fillChickenLocation(0, new HashSet<>(), chickenLocationConditions);
        return chickenLocationConditions;
    }

    private static void fillChickenLocation(int beginIndex, Set<RowAndCol> chickenLocations, List<Set<RowAndCol>> chickenLocationConditions) {
        if (chickenLocations.size() == CHICKEN_COUNT) {
            chickenLocationConditions.add(chickenLocations);
            return;
        }

        if (chickenLocations.size() > CHICKEN_COUNT) {
            return;
        }

        if (beginIndex >= CHICKENS.size()) {
            return;
        }

        Set<RowAndCol> newChickenLocations = new HashSet<>(chickenLocations);
        newChickenLocations.add(CHICKENS.get(beginIndex));
        fillChickenLocation(beginIndex + 1, newChickenLocations, chickenLocationConditions);
        fillChickenLocation(beginIndex + 1, new HashSet<>(chickenLocations), chickenLocationConditions);
    }

    private static int computeMinimumChickenDistanceSum(Set<RowAndCol> chickens) {
        int minDistanceSum = 0;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (MAP[row][col] == HOUSE) {
                    QUEUE.clear();
                    clearVisits();
                    minDistanceSum += computeMinimumChickenDistance(row, col, chickens);
                }
            }
        }

        return minDistanceSum;
    }

    private static void clearVisits() {
        for (boolean[] visitLine: VISITS) {
            Arrays.fill(visitLine, false);
        }
    }

    private static int computeMinimumChickenDistance(int row, int col, Set<RowAndCol> chickens) {
        QUEUE.add(new RowAndCol(row, col));
        int minDistance = Integer.MAX_VALUE;
        while (!QUEUE.isEmpty()) {
            RowAndCol rowAndCol = QUEUE.poll();
            int curRow = rowAndCol.row;
            int curCol = rowAndCol.col;
            if (chickens.contains(rowAndCol)) {
                minDistance = Math.min(minDistance, Math.abs(curRow - row) + Math.abs(curCol - col));
                break;
            }

            for (int i = 0 ; i < 4 ; i ++) {
                int nextRow = curRow + dy[i];
                int nextCol = curCol + dx[i];
                if ((0 <= nextRow && nextRow < SIZE && 0 <= nextCol && nextCol < SIZE) && !VISITS[nextRow][nextCol]) {
                    QUEUE.add(new RowAndCol(nextRow, nextCol));
                    VISITS[nextRow][nextCol] = true;
                }
            }
        }

        return minDistance;
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

        @Override
        public String toString() {
            return "{" +
                   "row=" + row +
                   ", col=" + col +
                   '}';
        }
    }
}
