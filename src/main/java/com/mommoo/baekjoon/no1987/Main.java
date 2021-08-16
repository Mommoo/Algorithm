package com.mommoo.baekjoon.no1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DY = {-1, 0, 1, 0};
    private static final int[] DX = {0, 1, 0, -1};

    private static int MAX_ROW;
    private static int MAX_COL;
    private static char[][] NODES;
    private static boolean[] ALPHABET_VISITS = new boolean[26];

    public static void main(String[] args) throws IOException {
        initData();
        ALPHABET_VISITS[NODES[0][0] -'A'] = true;
        System.out.println(findMaxVisitCount(0, 0, 1));
    }

    private static void initData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());
        NODES = new char[MAX_ROW][MAX_COL];

        for (int row = 0 ; row < MAX_ROW; row++) {
            String nodeLine = reader.readLine();
            for (int col = 0; col < MAX_COL; col++) {
                NODES[row][col] = nodeLine.charAt(col);
            }
        }
        reader.close();
    }

    private static boolean isValid(int row, int col) {
        return 0 <= row && row < MAX_ROW && 0 <= col && col < MAX_COL;
    }

    private static int findMaxVisitCount(int row, int col, int count) {
        int maxVisitCount = 0;

        for (int i = 0; i < 4; i++) {
            int nextRow = row + DY[i];
            int nextCol = col + DX[i];

            if (!isValid(nextRow, nextCol) || ALPHABET_VISITS[NODES[nextRow][nextCol] - 'A']) {
                maxVisitCount = Math.max(maxVisitCount, count);
                continue;
            }

            ALPHABET_VISITS[NODES[nextRow][nextCol] - 'A'] = true;
            maxVisitCount = Math.max(maxVisitCount, findMaxVisitCount(nextRow, nextCol, count+1));
            ALPHABET_VISITS[NODES[nextRow][nextCol] - 'A'] = false;
        }

        return maxVisitCount;
    }
}
