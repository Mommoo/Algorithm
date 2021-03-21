package com.mommoo.baekjoon.no2146;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int SEA = 0;
    private static final int ISLAND = 1;

    private static final int[] DY = {-1, 0, 1, 0};
    private static final int[] DX = {0, 1, 0, -1};

    private static int SIZE;
    private static int[][] NODES;

    private static boolean[][] VISITS;

    private static final Queue<Integer> POSITIONS = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        setUp();

        int minCost = Integer.MAX_VALUE;

        int firstIslandBeginPosition = -1;
        while ((firstIslandBeginPosition = findNoneVisitFirstIslandBeginPosition()) != -1) {
            clear();
            offerBeginPositions(firstIslandBeginPosition);

            if (findNoneVisitFirstIslandBeginPosition() == -1) {
                break;
            }

            minCost = Math.min(minCost, computeMinCost());
        }

        System.out.println(minCost);
    }

    private static void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SIZE = Integer.parseInt(reader.readLine());
        NODES = new int[SIZE][SIZE];
        for (int row = 0 ; row < SIZE; row++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < SIZE; col++) {
                NODES[row][col] = tokenizer.nextToken().charAt(0) - '0';
            }
        }
        VISITS = new boolean[SIZE][SIZE];
        reader.close();
    }

    private static int findNoneVisitFirstIslandBeginPosition() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!VISITS[row][col] && NODES[row][col] == ISLAND) {
                    return row * SIZE + col;
                }
            }
        }

        return -1;
    }

    private static void clear() {
        POSITIONS.clear();
        for (int row = 0 ; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (NODES[row][col] == ISLAND) {
                    continue;
                }

                VISITS[row][col] = false;
            }
        }
    }

    private static void offer(Queue<Integer> queue, int position) {
        offer(queue, position / SIZE, position % SIZE);
    }

    private static void offer(Queue<Integer> queue, int row, int col) {
        queue.offer(row * SIZE + col);
        VISITS[row][col] = true;
    }

    private static void offerBeginPositions(int firstIslandBeginPosition) {
        Queue<Integer> tmpQueue = new LinkedList<>();
        offer(tmpQueue, firstIslandBeginPosition);

        while (!tmpQueue.isEmpty()) {
            int position = tmpQueue.poll();
            int row = position / SIZE;
            int col = position % SIZE;

            boolean existNextNodeSea = false;
            for (int i = 0; i < 4; i++) {
                int nextRow = row + DY[i];
                int nextCol = col + DX[i];

                if (isInvalid(nextRow, nextCol)) {
                    continue;
                }

                if (VISITS[nextRow][nextCol]|| NODES[nextRow][nextCol] == SEA) {
                    existNextNodeSea = existNextNodeSea || NODES[nextRow][nextCol] == SEA;
                    continue;
                }

                offer(tmpQueue, nextRow, nextCol);
            }

            if (existNextNodeSea) {
                offer(POSITIONS, row, col);
            }
        }
    }

    private static boolean isInvalid(int row, int col) {
        return !(0 <= row && row < SIZE && 0 <= col && col < SIZE);
    }

    private static int computeMinCost() {
        int cost = 0;

        while (!POSITIONS.isEmpty()) {
            int size = POSITIONS.size();

            while (size-- > 0) {
                int position = POSITIONS.poll();
                int row = position / SIZE;
                int col = position % SIZE;

                for (int i = 0; i < 4; i++) {
                    int nextRow = row + DY[i];
                    int nextCol = col + DX[i];

                    if (isInvalid(nextRow, nextCol) || VISITS[nextRow][nextCol]) {
                        continue;
                    }

                    if (NODES[nextRow][nextCol] == ISLAND) {
                        return cost;
                    }

                    offer(POSITIONS, nextRow, nextCol);
                }
            }

            cost++;
        }

        return Integer.MAX_VALUE;
    }
}
