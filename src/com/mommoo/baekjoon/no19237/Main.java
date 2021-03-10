package com.mommoo.baekjoon.no19237;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static int[][][] NODES;
    private static Shark[] SHARKS;
    private static final int[][] DIRECTION = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        setUp();
        int time = 0;

        while (time < 1000) {
            moveSharks();

            time++;

            if (isRemainOnlyNumberOneShark()) {
                System.out.println(time);
                return;
            }
        }

        System.out.println(-1);
    }

    private static void setUp() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        NODES = new int[N][N][2];
        SHARKS = new Shark[M];

        int[][] sharkPositions = new int[M][2];
        for (int row = 0; row < N; row ++) {
            tokenizer = new StringTokenizer(br.readLine());

            for (int col = 0; col < N; col++) {
                int value = Integer.parseInt(tokenizer.nextToken());
                if (value > 0) {
                    sharkPositions[value - 1][0] = row;
                    sharkPositions[value - 1][1] = col;
                    NODES[row][col][0] = value;
                    NODES[row][col][1] = K;
                }
            }
        }

        tokenizer = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int curDirection = Integer.parseInt(tokenizer.nextToken()) - 1;
            int[][] directions = new int[4][4];
            for (int j = 0; j < 4; j++) {
                StringTokenizer directionPriority = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    directions[j][k] = (directionPriority.nextToken().charAt(0) - '0') - 1;
                }
            }

            SHARKS[i] = new Shark(sharkPositions[i][0], sharkPositions[i][1], curDirection, directions);
        }

        br.close();
    }

    private static void moveSharks() {
        for (int i = 0; i < M; i++) {
            moveShark(i);
        }
        decreaseSmell();
        putSmellAndRemoveShark();
    }

    private static void moveShark(int sharkIndex) {
        Shark shark = SHARKS[sharkIndex];
        if (shark == null) {
            return;
        }

        // 비어있는 칸을 찾아본다.
        int nextDirection = -1;
        for (int i = 0 ; i < 4; i++) {
            int d = shark.getNextDirection(i);
            int nextRow = shark.row + DIRECTION[d][0];
            int nextCol = shark.col + DIRECTION[d][1];

            if (isInvalid(nextRow, nextCol)) {
                continue;
            }

            if (NODES[nextRow][nextCol][0] == 0) {
                nextDirection = d;
                break;
            }
        }

        // 자신의 냄새가 있는 칸을 찾아본다.
        if (nextDirection == -1) {
            for (int i = 0; i < 4; i++) {
                int d = shark.getNextDirection(i);
                int nextRow = shark.row + DIRECTION[d][0];
                int nextCol = shark.col + DIRECTION[d][1];

                if (isInvalid(nextRow, nextCol)) {
                    continue;
                }

                if (NODES[nextRow][nextCol][0] == sharkIndex + 1) {
                    nextDirection = d;
                    break;
                }
            }
        }

        shark.move(nextDirection);
    }

    private static void putSmellAndRemoveShark() {
        for (int i = 0 ; i < M ; i++) {
            Shark shark = SHARKS[i];
            if (shark == null) {
                continue;
            }
            // 더 쌘 물고기가 이미 점유...
            // 비어있지 않고, 자신의 구역이 아니면
            if (NODES[shark.row][shark.col][0] != 0 && NODES[shark.row][shark.col][0] != i + 1) {
                SHARKS[i] = null;
                continue;
            }

            NODES[shark.row][shark.col][0] = i + 1;
            NODES[shark.row][shark.col][1] = K;
        }
    }

    private static boolean isInvalid(int row, int col) {
        return !(0 <= row && row < N && 0 <= col && col < N);
    }

    private static void decreaseSmell() {
        for (int row = 0 ; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (NODES[row][col][0] != 0) {
                    NODES[row][col][1]--;

                    // 냄새가 사라지면 점유한 상어를 지운다.
                    if (NODES[row][col][1] == 0) {
                        NODES[row][col][0] = 0;
                    }
                }
            }
        }
    }

    private static boolean isRemainOnlyNumberOneShark() {
        for (int i = 1; i < M; i++) {
            if (SHARKS[i] != null) {
                return false;
            }
        }

        return true;
    }

    private static class Shark {
        private int row;
        private int col;
        private int currentDirection;
        private final int[][] directions;

        public Shark(int row, int col, int currentDirection, int[][] directions) {
            this.row = row;
            this.col = col;
            this.currentDirection = currentDirection;
            this.directions = directions;
        }

        public int getNextDirection(int priority) {
            return directions[currentDirection][priority];
        }

        public void move(int direction) {
            this.currentDirection = direction;
            this.row += DIRECTION[direction][0];
            this.col += DIRECTION[direction][1];
        }
    }
}
