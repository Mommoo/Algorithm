package com.mommoo.baekjoon.no15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
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
        CHICKEN_LOCATION_CONDITIONS = createChickenLocationConditions();
        System.out.println(CHICKENS);
        System.out.println(CHICKEN_LOCATION_CONDITIONS.size());
        System.out.println(CHICKEN_LOCATION_CONDITIONS);
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
