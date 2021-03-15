package com.mommoo.baekjoon.no13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final char R = 'R';
    private static final char B = 'B';
    private static final char HOLE = 'O';

    private static Position RED;
    private static Position BLUE;

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private static final int[] DY = {0, 0, -1, 1};
    private static final int[] DX = {1, -1, 0, 0};

    private static int MAX_ROW;
    private static int MAX_COL;
    private static char[][] NODES;
    private static boolean[][][][] VISITS;

    private static final Queue<Balls> QUEUE = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        setUp();

        QUEUE.offer(new Balls(RED, BLUE));
        VISITS[RED.row][RED.col][BLUE.row][BLUE.col] = true;

        System.out.println(count());
    }

    private static void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());

        setUpNodes(reader);

        VISITS = new boolean[MAX_ROW][MAX_COL][MAX_ROW][MAX_COL];

        reader.close();
    }

    private static void setUpNodes(BufferedReader reader) throws IOException {
        NODES = new char[MAX_ROW][MAX_COL];
        for (int row = 0; row < MAX_ROW; row++) {
            String line = reader.readLine();
            for (int col = 0; col < MAX_COL; col++) {
                char c = line.charAt(col);
                if (c == R) {
                    RED = new Position(row, col);
                } else if (line.charAt(col) == B) {
                    BLUE = new Position(row, col);
                }

                if (c == 'R' || c == 'B') {
                    NODES[row][col] = '.';
                } else {
                    NODES[row][col] = line.charAt(col);
                }
            }
        }
    }

    private static int count() {
        int count = 0;
        while (!QUEUE.isEmpty() && count < 11) {
            int size = QUEUE.size();

            while (size-- > 0) {
                Balls balls = QUEUE.poll();

                if (NODES[balls.BLUE.row][balls.BLUE.col] == HOLE) {
                    continue;
                } else if (NODES[balls.RED.row][balls.RED.col] == HOLE) {
                    return count;
                }

                for (int direction = 0; direction < 4; direction++) {
                    Ball[] _balls = balls.computeOrdinary(direction);
                    Position firstPosition = findNextPosition(direction, new Position(_balls[0].row, _balls[0].col), new Position(_balls[1].row, _balls[1].col));
                    Position secondPosition = findNextPosition(direction, new Position(_balls[1].row, _balls[1].col), firstPosition);

                    if (_balls[0].name == R) {
                        if (VISITS[firstPosition.row][firstPosition.col][secondPosition.row][secondPosition.col]) {
                            continue;
                        }

                        VISITS[firstPosition.row][firstPosition.col][secondPosition.row][secondPosition.col] = true;
                    } else if (_balls[0].name == B) {
                        if (VISITS[secondPosition.row][secondPosition.col][firstPosition.row][firstPosition.col]) {
                            continue;
                        }

                        VISITS[secondPosition.row][secondPosition.col][firstPosition.row][firstPosition.col] = true;
                    }

                    Balls next = _balls[0].name == R ? new Balls(firstPosition, secondPosition) : new Balls(secondPosition, firstPosition);
                    QUEUE.offer(next);
                }
            }

            count++;
        }

        return -1;
    }

    private static boolean isInvalid(int row, int col) {
        return !(0 <= row && row < MAX_ROW && 0 <= col && col < MAX_COL);
    }

    private static Position findNextPosition(int direction, Position moveBall, Position blockBall) {
        int nextRow = moveBall.row;
        int nextCol = moveBall.col;

        while (true) {
            nextRow += DY[direction];
            nextCol += DX[direction];

            if (isInvalid(nextRow, nextCol)) {
                nextRow -= DY[direction];
                nextCol -= DX[direction];
                break;
            }

            if (NODES[nextRow][nextCol] == HOLE) {
                break;
            }

            boolean isBlockedByOtherBall = blockBall.equals(nextRow, nextCol);
            if (NODES[nextRow][nextCol] == '#' || isBlockedByOtherBall) {
                nextRow -= DY[direction];
                nextCol -= DX[direction];
                break;
            }
        }

        return new Position(nextRow, nextCol);
    }

    private static class Balls {
        private final Ball RED;
        private final Ball BLUE;

        public Balls(Position RED, Position BLUE) {
            this.RED = Ball.createRed(RED.row, RED.col);
            this.BLUE = Ball.createBlue(BLUE.row, BLUE.col);
        }

        public Ball[] computeOrdinary(int direction) {
            switch (direction) {
                case UP: return RED.row < BLUE.row ? new Ball[]{RED, BLUE} : new Ball[]{BLUE, RED};
                case DOWN: return RED.row > BLUE.row ? new Ball[]{RED, BLUE} : new Ball[]{BLUE, RED};
                case LEFT: return RED.col < BLUE.col ? new Ball[]{RED, BLUE} : new Ball[]{BLUE, RED};
                case RIGHT: return RED.col > BLUE.col ? new Ball[]{RED, BLUE} : new Ball[]{BLUE, RED};
                default: return new Ball[0];
            }
        }
    }

    private static class Ball {
        private final char name;
        private final int row;
        private final int col;

        public Ball(char name, int row, int col) {
            this.name = name;
            this.row = row;
            this.col = col;
        }

        public static Ball createRed(int row, int col) {
            return new Ball(R, row, col);
        }

        public static Ball createBlue(int row, int col) {
            return new Ball(B, row, col);
        }
    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean equals(int row, int col) {
            return this.row == row && this.col == col;
        }
    }
}
