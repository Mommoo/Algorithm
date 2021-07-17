package com.mommoo.baekjoon.no1652;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final char EMPTY = '.';
    private static final char BUNDLE = 'X';
    private static int SIZE;
    private static char[][] MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SIZE = Integer.parseInt(reader.readLine());
        MAP = createMap(reader);
        reader.close();

        System.out.println(computeRowLayDownCount() + " " + computeColLayDownCount());
    }

    public static char[][] createMap(BufferedReader reader) throws IOException {
        char[][] map = new char[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            String mapLine = reader.readLine();
            for (int col = 0; col < SIZE; col++) {
                map[row][col] = mapLine.charAt(col);
            }
        }

        return map;
    }

    public static int computeColLayDownCount() {
        int total = 0;

        for (int col = 0; col < SIZE; col++) {
            int beginRow = -1;
            boolean isFindBeginRow = false;

            for (int row = 0; row < SIZE; row++) {
                char mapNode = MAP[row][col];

                if (!isFindBeginRow && mapNode == EMPTY) {
                    isFindBeginRow = true;
                    beginRow = row;
                }

                if (isFindBeginRow && (mapNode == BUNDLE || row == SIZE - 1)) {
                    int len = row - beginRow;

                    if (row == SIZE - 1 && mapNode == EMPTY) {
                        len += 1;
                    }

                    if (len >= 2) {
                        total++;
                    }
                    isFindBeginRow = false;
                }
            }
        }

        return total;
    }

    public static int computeRowLayDownCount() {
        int total = 0;

        for (int row = 0; row < SIZE; row ++) {
            int beginCol = -1;
            boolean isFindBeginCol = false;

            for (int col = 0; col < SIZE; col++) {
                char mapNode = MAP[row][col];

                if (!isFindBeginCol && mapNode == EMPTY) {
                    isFindBeginCol = true;
                    beginCol = col;
                }

                if (isFindBeginCol && (mapNode == BUNDLE || col == SIZE - 1)) {
                    int len = col - beginCol;

                    if (col == SIZE - 1 && mapNode == EMPTY) {
                        len += 1;
                    }

                    if (len >= 2) {
                        total++;
                    }

                    isFindBeginCol = false;
                }
            }
        }

        return total;
    }
}
