package com.mommoo.baekjoon.no14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    private static final int[] DY = {-1, 0, 1, 0};
    private static final int[] DX = {0, 1, 0, -1};

    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int CLEAR = 2;

    private static int MAX_ROW;
    private static int MAX_COL;
    private static Robot ROBOT;
    private static int[][] NODES;

    public static void main(String[] args) throws IOException {
        setUp();

        int clearCount = 0;
        while (true) {
            if (NODES[ROBOT.row][ROBOT.col] == EMPTY) {
                clearCount++;
                NODES[ROBOT.row][ROBOT.col] = CLEAR;
            }

            int nextDirection = findNextDirection(ROBOT);

            if (nextDirection == -1) {
                int backDirection = rotate(rotate(ROBOT.direction));
                int nextRow = ROBOT.row + DY[backDirection];
                int nextCol = ROBOT.col + DX[backDirection];

                if (NODES[nextRow][nextCol] == WALL) {
                    break;
                }

                ROBOT = new Robot(ROBOT.direction, nextRow, nextCol);
                continue;
            }

            int nextRow = ROBOT.row + DY[nextDirection];
            int nextCol = ROBOT.col + DX[nextDirection];
            ROBOT = new Robot(nextDirection, nextRow, nextCol);
        }

        System.out.println(clearCount);
    }

    private static void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        int r = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());
        int d = Integer.parseInt(tokenizer.nextToken());
        ROBOT = new Robot(d, r, c);

        NODES = new int[MAX_ROW][MAX_COL];
        for (int row = 0 ; row < MAX_ROW; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < MAX_COL; col++) {
                NODES[row][col] = tokenizer.nextToken().charAt(0) - '0';
            }
        }

        reader.close();

    }

    private static int findNextDirection(Robot robot) {
        int currentDirection = robot.direction;
        for (int i = 0; i < 4; i++) {
            int nextDirection = rotate(currentDirection);
            int nextRow = robot.row + DY[nextDirection];
            int nextCol = robot.col + DX[nextDirection];

            if (isValid(nextRow, nextCol) && NODES[nextRow][nextCol] == EMPTY) {
                return nextDirection;
            }
            currentDirection = nextDirection;
        }

        return -1;
    }

    private static boolean isValid(int row, int col) {
        return 0 <= row && row < MAX_ROW && 0 <= col && col < MAX_COL;
    }

    private static int rotate(int direction) {
        if (direction == UP) {
            return LEFT;
        } else {
            return direction - 1;
        }
    }

    private static class Robot {
        private final int direction;
        private final int row;
        private final int col;

        public Robot(int direction, int row, int col) {
            this.direction = direction;
            this.row = row;
            this.col = col;
        }
    }
}
