package com.mommoo.baekjoon.no7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DZ = {0, 0, 0, 0, -1, 1};
    private static final int[] DY = {1, 0 , -1, 0, 0 ,0};
    private static final int[] DX = {0, 1, 0, -1, 0, 0};

    private static final int GOOD_TOMATO = 1;
    private static final int NORMAL_TOMATO = 0;

    private static int[][][] NODES;
    private static boolean[][][] VISITS;
    private static final Queue<Position> POSITIONS = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        NODES = createNodes();
        VISITS = new boolean[NODES.length][NODES[0].length][NODES[0][0].length];

        offerGoodTomatoPositions();
        int days = processMakeGoodTomatoes();

        System.out.println(existNormalTomato() ? -1 : days);
    }

    private static int[][][] createNodes() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());
        int H = Integer.parseInt(tokenizer.nextToken());

        int [][][] nodes = new int[H][N][M];

        for (int level = 0; level < H; level++) {
            for (int row = 0; row < N; row++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int col = 0; col < M; col++) {
                    nodes[level][row][col] = Integer.parseInt(tokenizer.nextToken());
                }
            }
        }

        reader.close();

        return nodes;
    }

    private static void offerGoodTomatoPositions() {
        for (int level = 0; level < NODES.length; level++) {
            for (int row = 0; row < NODES[level].length; row++) {
                for (int col = 0; col < NODES[level][row].length; col++) {
                     if (NODES[level][row][col] == GOOD_TOMATO) {
                         offerTomatoPosition(new Position(level, row, col));
                     }
                }
            }
        }
    }

    private static void offerTomatoPosition(Position position) {
        VISITS[position.level][position.row][position.col] = true;
        NODES[position.level][position.row][position.col] = GOOD_TOMATO;
        POSITIONS.offer(position);
    }

    private static int processMakeGoodTomatoes() {
        int days = -1;

        while (!POSITIONS.isEmpty()) {
            days++;
            int count = POSITIONS.size();

            while (count-- > 0) {
                Position curPosition = POSITIONS.poll();
                for (int i = 0 ; i < 6; i++) {
                    int nextLevel = curPosition.level + DZ[i];
                    int nextRow = curPosition.row + DY[i];
                    int nextCol = curPosition.col + DX[i];
                    if (isInvalid(nextLevel, nextRow, nextCol) || !isNormalTomato(nextLevel, nextRow, nextCol) || VISITS[nextLevel][nextRow][nextCol]) {
                        continue;
                    }

                    Position nextPosition = new Position(nextLevel, nextRow, nextCol);
                    offerTomatoPosition(nextPosition);
                }
            }
        }

        return days;
    }

    private static boolean existNormalTomato() {
        for (int level = 0; level < NODES.length; level++) {
            for (int row = 0; row < NODES[level].length; row++) {
                for (int col = 0; col < NODES[level][row].length; col++) {
                    if (NODES[level][row][col] == NORMAL_TOMATO) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean isInvalid(int level, int row, int col) {
        return !(0<= level && level < NODES.length && 0<= row && row < NODES[level].length && 0 <= col && col < NODES[level][row].length);
    }

    private static boolean isNormalTomato(int level, int row, int col) {
        return NODES[level][row][col] == NORMAL_TOMATO;
    }

    private static class Position {
        private final int level;
        private final int row;
        private final int col;

        public Position(int level, int row, int col) {
            this.level = level;
            this.row = row;
            this.col = col;
        }

        public Position next(int dz, int dy, int dx) {
            return new Position(level + dz, row + dy, col + dx);
        }
    }
}
