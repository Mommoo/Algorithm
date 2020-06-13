package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ2580 {
    private static final int LEN = 9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = createMap(br);
        Queue<RowAndCol> absentNumbers = findAbsentNumbers(map);
        while (!absentNumbers.isEmpty()) {
            if (filledAbsentNumberIfPossible(map, absentNumbers.peek())) {
                absentNumbers.poll();
            }
        }
        printMap(map);
    }

    private static int[][] createMap(BufferedReader br) throws IOException {
        int[][] map = new int[LEN][LEN];

        for (int row = 0; row < LEN ; row ++) {
            StringTokenizer mapLine = new StringTokenizer(br.readLine());
            for (int col = 0; col < LEN; col++) {
                map[row][col] = Integer.parseInt(mapLine.nextToken());
            }
        }

        return map;
    }

    private static Queue<RowAndCol> findAbsentNumbers(int[][] map) {
        Queue<RowAndCol> absentNumbers = new LinkedList<>();

        for (int row = 0 ; row < map.length; row ++) {
            for (int col = 0; col < map.length; col++) {
                if (map[row][col] != 0) {
                    continue;
                }
                absentNumbers.offer(new RowAndCol(row, col));
            }
        }

        return absentNumbers;
    }

    private static boolean filledAbsentNumberIfPossible(int[][] map, RowAndCol absentNumberRowAndCol) {
        Set<Integer> possibleAbsentNumbersInColumns = findPossibleAbsentNumbersInColumns(map, absentNumberRowAndCol);
        if (putNumberIfConditionOne(map, possibleAbsentNumbersInColumns, absentNumberRowAndCol)) {
            return true;
        }

        Set<Integer> possibleAbsentNumbersInRows = findPossibleAbsentNumbersInRows(map, absentNumberRowAndCol);
        if (putNumberIfConditionOne(map, possibleAbsentNumbersInRows, absentNumberRowAndCol)) {
            return true;
        }

        Set<Integer> possibleAbsentNumbersInSquare = findPossibleAbsentNumbersInSquare(map, absentNumberRowAndCol);
        if (putNumberIfConditionOne(map, possibleAbsentNumbersInSquare, absentNumberRowAndCol)) {
            return true;
        }

        Set<Integer> intersectSet = computeIntersectSet(possibleAbsentNumbersInRows, possibleAbsentNumbersInColumns);
        if (putNumberIfConditionOne(map, intersectSet, absentNumberRowAndCol)) {
            return true;
        }

        intersectSet = computeIntersectSet(possibleAbsentNumbersInRows, possibleAbsentNumbersInSquare);
        if (putNumberIfConditionOne(map, intersectSet, absentNumberRowAndCol)) {
            return true;
        }

        intersectSet = computeIntersectSet(possibleAbsentNumbersInColumns, possibleAbsentNumbersInSquare);
        return putNumberIfConditionOne(map, intersectSet, absentNumberRowAndCol);
    }

    private static boolean putNumberIfConditionOne(int[][] map, Set<Integer> absentNumbers, RowAndCol rowAndCol) {
        if (absentNumbers.size() != 1) {
            return false;
        }

        int number = absentNumbers.iterator().next();
        map[rowAndCol.row][rowAndCol.col] = number;
        return true;
    }

    private static Set<Integer> createAllNumbers() {
        return IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toSet());
    }

    private static Set<Integer> findPossibleAbsentNumbersInColumns(int[][] map, RowAndCol absentNumberRowAndCol) {
        int row = absentNumberRowAndCol.row;

        Set<Integer> possibleAbsentNumbers = createAllNumbers();

        for (int col = 0 ; col < 9; col++) {
            int number = map[row][col];
            possibleAbsentNumbers.remove(number);
        }

        return possibleAbsentNumbers;
    }

    private static Set<Integer> findPossibleAbsentNumbersInRows(int[][] map, RowAndCol absentNumberRowAndCol) {
        int col = absentNumberRowAndCol.col;

        Set<Integer> possibleAbsentNumbers = createAllNumbers();

        for (int row = 0 ; row < 9; row++) {
            int number = map[row][col];
            possibleAbsentNumbers.remove(number);
        }

        return possibleAbsentNumbers;
    }

    private static Set<Integer> findPossibleAbsentNumbersInSquare(int [][] map, RowAndCol absentNumberRowAndCol) {
        int row = absentNumberRowAndCol.row - (absentNumberRowAndCol.row % 3);
        int col = absentNumberRowAndCol.col - (absentNumberRowAndCol.col % 3);

        Set<Integer> possibleAbsentNumbers = createAllNumbers();

        for (int i = 0 ; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int nextRow = row + i;
                int nextCol = col + j;

                int number = map[nextRow][nextCol];
                possibleAbsentNumbers.remove(number);
            }
        }

        return possibleAbsentNumbers;
    }

    private static Set<Integer> computeIntersectSet(Set<Integer> set1, Set<Integer> set2) {
        return set1.stream()
                   .filter(set2::contains)
                   .collect(Collectors.toSet());
    }

    private static void printMap(int[][] map) {
        StringBuilder builder = new StringBuilder();

        for (int row = 0 ; row < map.length; row ++) {
            for (int col = 0; col < map.length; col++) {
                builder.append(map[row][col]).append(" ");
            }

            builder.append("\n");
        }

        System.out.println(builder);
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
