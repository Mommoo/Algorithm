package com.mommoo.baekjoon.no2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int WALL = 1;

    private static final int[] DX = {-1, 0, 1, 0};
    private static final int[] DY = {0, 1, 0, -1};

    private static int MAX_ROW;
    private static int MAX_COL;
    private static int[][] NODES;
    private static boolean[][][] VISITS;
    private static Queue<Node> QUEUE = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        setUp();
        System.out.println(computeMinimumDestinationCost(0, 0));
    }

    private static void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        MAX_ROW = Integer.parseInt(tokenizer.nextToken());
        MAX_COL = Integer.parseInt(tokenizer.nextToken());

        NODES = new int[MAX_ROW][MAX_COL];

        for (int row = 0 ; row < MAX_ROW; row++) {
            String nodeLine = reader.readLine();
            for (int col = 0; col < MAX_COL; col++) {
                NODES[row][col] = nodeLine.charAt(col) - '0';
            }
        }

        VISITS = new boolean[MAX_ROW][MAX_COL][2];

        reader.close();
    }

    private static void offer(int row, int col, boolean crush) {
        VISITS[row][col][crush? 1 : 0] = true;
        QUEUE.offer(new Node(row, col, crush));
    }

    private static boolean isVisit(int row, int col, boolean crush) {
        return VISITS[row][col][crush? 1 : 0];
    }

    private static void offerAllDirection(Node curNode, boolean canCrush) {
        for (int i = 0 ; i < 4; i++) {
            int nextRow = curNode.row + DY[i];
            int nextCol = curNode.col + DX[i];

            if (isInvalid(nextRow, nextCol) || isVisit(nextRow, nextCol, curNode.crush) || (!canCrush && NODES[nextRow][nextCol] == WALL)) {
                continue;
            }

            offer(nextRow, nextCol, canCrush || curNode.crush);
        }
    }

    private static int computeMinimumDestinationCost(int beginRow, int beginCol) {
        offer(beginRow, beginCol, false);

        int cost = 0;
        while (!QUEUE.isEmpty()) {
            cost++;
            int size = QUEUE.size();

            while (size-- > 0) {
                Node node = QUEUE.poll();

                if (node.isLastNode()) {
                    return cost;
                }

                offerAllDirection(node, false);

                if (node.crush) {
                    continue;
                }

                offerAllDirection(node, true);
            }
        }

        return -1;
    }

    private static boolean isInvalid(int row, int col) {
        return !(0 <= row && row < MAX_ROW && 0 <= col && col < MAX_COL);
    }

    private static class Node {
        private final int row;
        private final int col;
        private final boolean crush;

        public Node(int row, int col, boolean crush) {
            this.row = row;
            this.col = col;
            this.crush = crush;
        }

        public boolean isLastNode() {
            return this.row == MAX_ROW - 1 && this.col == MAX_COL - 1;
        }
    }
}
