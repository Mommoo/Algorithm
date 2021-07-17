package com.mommoo.baekjoon.no17086;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FastMain {
    private static final int BLANK = 0;
    private static final int SHARK = 1;

    private static final int[] DX = {0, 0, 1, 1, 1, -1, -1, -1};
    private static final int[] DY = {1, -1, 0, 1, -1, 0, 1, -1};

    private static final Queue<Position> POSITIONS = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        int[][] nodes = createNodesAndFillPositions();
        markCost(nodes);
        System.out.println(findMaxCost(nodes));
    }

    private static int[][] createNodesAndFillPositions() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int R = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());

        int[][] nodes = new int[R][C];

        for (int row = 0; row < R; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col =0; col < C; col++) {
                nodes[row][col] = tokenizer.nextToken().charAt(0) - '0';

                if (nodes[row][col] == SHARK) {
                    POSITIONS.offer(new Position(row, col));
                }
            }
        }

        reader.close();
        return nodes;
    }

    private static void markCost(int[][] nodes) {
        int depth = 1;

        while (!POSITIONS.isEmpty()) {
            int size = POSITIONS.size();

            while (size-- > 0) {

                Position p = POSITIONS.poll();

                for (int i = 0; i < 8; i++) {
                    int nextRow = p.row + DY[i];
                    int nextCol = p.col + DX[i];

                    if (!isValid(nextRow, nextCol, nodes.length, nodes[0].length)) {
                        continue;
                    }

                    if (nodes[nextRow][nextCol] == BLANK) {
                        nodes[nextRow][nextCol] = depth;
                        POSITIONS.offer(new Position(nextRow, nextCol));
                    }
                }
            }
            depth++;
        }
    }

    private static boolean isValid(int row, int col, int maxRow, int maxCol) {
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }

    private static int findMaxCost(int[][] nodes) {
        int maxCost = Integer.MIN_VALUE;
        for (int row = 0; row < nodes.length; row++) {
            for (int col = 0; col < nodes[row].length; col++) {
                maxCost = Math.max(nodes[row][col], maxCost);
            }
        }

        return maxCost;
    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
