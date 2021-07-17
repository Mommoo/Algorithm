package com.mommoo.baekjoon.no2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static int SIZE;
    private static int[][] NODES;
    private static boolean[][] VISIT;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SIZE = Integer.parseInt(reader.readLine());
        NODES = createNodes(reader);
        reader.close();

        VISIT = new boolean[SIZE][SIZE];

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (VISIT[row][col]) {
                    continue;
                }

                if (NODES[row][col] == 1) {
                    queue.add(countNodeGroup(row, col));
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(queue.size())
               .append("\n");

        while (!queue.isEmpty()) {
            builder.append(queue.poll())
                   .append('\n');
        }

        System.out.println(builder);
    }

    private static int[][] createNodes(BufferedReader reader) throws IOException {
        int[][] nodes = new int[SIZE][SIZE];
        for (int row = 0 ; row < SIZE; row ++) {
            String nodeLine = reader.readLine();
            for (int col = 0; col < SIZE; col ++) {
                nodes[row][col] = nodeLine.charAt(col) - '0';
            }
        }
        return nodes;
    }

    private static int countNodeGroup(int row, int col) {
        RowAndCol rowAndCol = new RowAndCol(row, col);
        VISIT[rowAndCol.row][rowAndCol.col] = true;

        Deque<RowAndCol> stack = new LinkedList<>();
        stack.push(rowAndCol);

        int count = 0;

        while (!stack.isEmpty()) {
            RowAndCol node = stack.pop();
            count++;

            for (int i = 0; i < 4; i++) {
                RowAndCol nextNode = node.next(dx[i], dy[i]);
                if (nextNode.isInvalid() || VISIT[nextNode.row][nextNode.col] || NODES[nextNode.row][nextNode.col] == 0) {
                    continue;
                }
                VISIT[nextNode.row][nextNode.col] = true;
                stack.push(nextNode);
            }
        }

        return count;
    }

    private static class RowAndCol {
        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public RowAndCol next(int dx, int dy) {
            return new RowAndCol(this.row + dy, this.col + dx);
        }

        public boolean isInvalid() {
            return !(0 <= row && row < SIZE && 0<= col && col < SIZE);
        }

        @Override
        public String toString() {
            return "RowAndCol{" +
                   "row=" + row +
                   ", col=" + col +
                   '}';
        }
    }
}
