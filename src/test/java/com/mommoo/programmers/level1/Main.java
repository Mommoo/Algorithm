package com.mommoo.programmers.level1;

import org.junit.jupiter.api.Test;

class Main {
    private static final int[] DELTA_ROW = {1, 0, -1, 0};
    private static final int[] DELTA_COL = {0, 1, 0, -1};

    private static final char PERSON = 'P';
    private static final char EMPTY_TABLE = 'O';
    private static final char PARTITION = 'X';

    @Test
    void test() {
        String[][] places = new String[][] {
                new String[] {"POXXX", "OXXXX", "XXXXX", "XXXXX", "XXXXX"},
                new String[] {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                new String[] {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                new String[] {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                new String[] {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };

        int[] answer = {1, 0, 1, 1, 1};
        int[] result = new int[5];
        for (int i = 0; i < 5; i++) {
            boolean hasSafeDistance = hasSafeDistance(places[i]);
            result[i] = hasSafeDistance ? 1 : 0;
        }
    }

    public static boolean hasSafeDistance(String[] place) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                char c = placeAt(row, col, place);
//                System.out.println("foo");
                if (c == PERSON && isTooClose(row, col, place, 0, -1, -1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isTooClose(int row, int col, String[] place, int distance, int prevRow, int prevCol) {
        if (distance > 2) {
            return false;
        }

        if (distance == 1 && placeAt(row, col, place) == PERSON) {
            return true;
        }

        if (distance == 2 && placeAt(prevRow, prevCol, place) != PARTITION && placeAt(row, col, place) == PERSON) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + DELTA_ROW[i];
            int nextCol = col + DELTA_COL[i];

            if (isInvalid(nextRow, nextCol) || (nextRow == prevRow && nextCol == prevCol)) {
                continue;
            }

            boolean isTooClose = isTooClose(nextRow, nextCol, place, distance+1, row, col);
            if (isTooClose) {
                return true;
            }
        }

        return false;
    }

    private static boolean isInvalid(int row, int col) {
        return !(0 <= row && row < 5 && 0 <= col && col < 5);
    }

    private static char placeAt(int row, int col, String[] place) {
        return place[row].charAt(col);
    }
}
