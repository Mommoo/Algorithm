package com.mommoo.baekjoon.no14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    private static final char EMPTY = '0';
    private static final char WALL = '1';
    private static final char VIRUS = '2';
    private static final char VISIT_VIRUS = '3';

    private static int MAX_ROW;
    private static int MAX_COL;
    private static char[][] NODES;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());
        NODES = createNodes(reader);
        System.out.println(dfs(3, new RowAndCol[3], 0, -1));
    }

    private static char[][] createNodes(BufferedReader reader) throws IOException {
        char[][] nodes = new char[MAX_ROW][MAX_COL];
        for (int row = 0; row < MAX_ROW; row++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < MAX_COL; col++) {
                nodes[row][col] = tokenizer.nextToken().charAt(0);
            }
        }
        return nodes;
    }

    private static int dfs(int wallCount, RowAndCol[] wallRowAndCols, int beginRow, int beginCol) {
        if (wallCount == 0) {
            return computeSafeArea(wallRowAndCols);
        }

        int maxSafeArea = 0;
        boolean first = true;
        for (int row = beginRow; row < MAX_ROW; row++) {
            int col = first ? beginCol + 1 : 0;
            if (first) {
                first = false;
            }
            for (; col < MAX_COL; col++) {
                if (NODES[row][col] != EMPTY) {
                    continue;
                }

                RowAndCol[] newWallRowAndCols = Arrays.copyOf(wallRowAndCols, wallRowAndCols.length);
                newWallRowAndCols[wallCount - 1] = new RowAndCol(row, col);
                maxSafeArea = Math.max(maxSafeArea, dfs(wallCount - 1, newWallRowAndCols, row, col));
            }
        }

        return maxSafeArea;
    }

    private static int computeSafeArea(RowAndCol[] wallRowAndCols) {
        char[][] newNodes = new char[MAX_ROW][];
        for (int row = 0 ; row < MAX_ROW; row++) {
            newNodes[row] = Arrays.copyOf(NODES[row], MAX_COL);
        }

        for (RowAndCol rowAndCol : wallRowAndCols) {
            newNodes[rowAndCol.row][rowAndCol.col] = WALL;
        }

        for (int row = 0 ; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                if (newNodes[row][col] == VIRUS) {
                    spreadVirus(row, col, newNodes);
                }
            }
        }

        int safeAreaCount = 0;
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                if (newNodes[row][col] == EMPTY) {
                    safeAreaCount++;
                }
            }
        }

        return safeAreaCount;
    }

    private static void spreadVirus(int row, int col, char[][] nodes) {
        if (isInvalid(row, col) || (nodes[row][col] == WALL || nodes[row][col] == VISIT_VIRUS)) {
            return;
        }

        nodes[row][col] = VISIT_VIRUS;
        for (int i = 0; i < 4; i++) {
            spreadVirus(row + dy[i], col + dx[i], nodes);
        }
    }

    private static boolean isInvalid(int row, int col) {
        return !(0 <= row && row < MAX_ROW && 0 <= col && col < MAX_COL);
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
