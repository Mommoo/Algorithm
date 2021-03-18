package com.mommoo.baekjoon.no2578;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static final boolean[][] BINGO = new boolean[5][5];
    private static Map<Integer, Integer> INDEX_FINDER = new HashMap<>();
    private static int bingoCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        setUp(reader);

        for (int i = 0 ; i < 5; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 5; j++){
                int count = (i * 5 + j) + 1;
                Integer callValue = Integer.parseInt(tokenizer.nextToken());
                checkBingoCount(callValue);

                if (bingoCount >= 3) {
                    System.out.println(count);
                    return;
                }
            }
        }

        reader.close();
    }

    private static void setUp(BufferedReader reader) throws IOException {
        for (int row = 0; row < 5; row ++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < 5; col++) {
                INDEX_FINDER.put(Integer.parseInt(tokenizer.nextToken()), row * 5 + col);
            }
        }
    }

    private static void checkBingoCount(Integer callValue) {
        int position = INDEX_FINDER.get(callValue);
        int row = position / 5;
        int col = position % 5;

        BINGO[row][col] = true;

        boolean isLeftDiagonalPosition = row == col;
        if (isLeftDiagonalPosition) {
            bingoCount += isLeftDiagonalBingo() ? 1 : 0;
        }

        boolean isRightDiagonalPosition = row + col == 4;
        if (isRightDiagonalPosition) {
            bingoCount += isRightDiagonalBingo() ? 1 : 0;
        }

        bingoCount += isRowBingo(col) ? 1 : 0;
        bingoCount += isColBingo(row) ? 1 : 0;
    }

    private static boolean isLeftDiagonalBingo() {
        for (int i = 0; i < 5; i++) {
            if (!BINGO[i][i]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isRightDiagonalBingo() {
        for (int row = 0; row < 5; row++) {
            int col = 4 - row;
            if (!BINGO[row][col]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isRowBingo(int col) {
        for (int row = 0; row < 5; row++) {
            if (!BINGO[row][col]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isColBingo(int row) {
        for (int col = 0; col < 5; col++) {
            if (!BINGO[row][col]) {
                return false;
            }
        }

        return true;
    }
}
