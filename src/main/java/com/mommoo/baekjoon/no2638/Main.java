package com.mommoo.baekjoon.no2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final char EMPTY = '0';
    private static final char CHEESE = '1';

    private static final int[] DY = new int[]{-1, 0, 1, 0};
    private static final int[] DX = new int[]{0, -1, 0, 1};

    private static int MAX_ROW;
    private static int MAX_COL;
    private static char[][] NODES;
    private static boolean[][] VISITS;

    public static void main(String[] args) throws IOException {
        initData();

        int time = 0;

        while (scan()) {
            time++;
        }

        System.out.println(time);
    }

    private static void initData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());
        NODES = new char[MAX_ROW][MAX_COL];
        VISITS = new boolean[MAX_ROW][MAX_COL];

        for (int row = 0; row < MAX_ROW; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < MAX_COL; col++) {
                NODES[row][col] = tokenizer.nextToken().charAt(0);
            }
        }
    }

    private static void init() {
        for (int row = 0; row < MAX_ROW; row++) {
            Arrays.fill(VISITS[row], false);
        }
    }

    private static boolean scan() {
        init();
        DFS(0, 0);
        boolean isExistCheese = false;
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                boolean isCheese = NODES[row][col] >= CHEESE;
                if (isCheese) {
                    isExistCheese = true;
                }

                boolean isContact = NODES[row][col] >= '3';
                if (isCheese && isContact) {
                    NODES[row][col] = EMPTY;
                }

                boolean isOneContact = NODES[row][col] == '2';
                if (isOneContact) {
                    NODES[row][col] = CHEESE;
                }
            }
        }

        return isExistCheese;
    }

    private static void DFS(int row, int col) {
        for (int i = 0 ; i < 4; i++) {
            int nextRow = row + DY[i];
            int nextCol = col + DX[i];

            if (!(0 <= nextRow && nextRow < MAX_ROW && 0 <= nextCol && nextCol < MAX_COL)) {
                continue;
            }

            if (NODES[nextRow][nextCol] >= CHEESE) {
                NODES[nextRow][nextCol]++;
                continue;
            }

            if (VISITS[nextRow][nextCol]) {
                continue;
            }

            VISITS[nextRow][nextCol] = true;
            DFS(nextRow, nextCol);
        }
    }
}
