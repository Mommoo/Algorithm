package com.mommoo.baekjoon.no17086;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DX = {0, 0, 1, 1, 1, -1, -1, -1};
    private static final int[] DY = {1, -1, 0, 1, -1, 0, 1, -1};
    private static final int SHARK = 1;
    private static final int BLANK = 0;
    private static boolean[][] VISITS;

    public static void main(String[] args) throws Exception {
        int[][] nodes = createNodes();
        VISITS = new boolean[nodes.length][nodes[0].length];

        int maxSafeLen = Integer.MIN_VALUE;
        for (int row = 0; row < nodes.length; row ++) {
            for (int col = 0; col < nodes[0].length; col++) {
                if (nodes[row][col] == BLANK) {
                    maxSafeLen = Math.max(maxSafeLen, findSafePathLen(nodes, row, col));
                }
            }
        }

        System.out.println(maxSafeLen);
    }

    private static int[][] createNodes() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int R = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());

        int[][] nodes = new int[R][C];

        for (int row = 0; row < R; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col =0; col < C; col++) {
                nodes[row][col] = tokenizer.nextToken().charAt(0) - '0';
            }
        }

        return nodes;
    }

    private static int findSafePathLen(int[][] nodes, int row, int col) {
        Queue<Node> _nodes = new LinkedList<>();
        _nodes.offer(new Node(row, col, 0));

        int safeLen = -1;

        while (!_nodes.isEmpty()) {
            Node node = _nodes.poll();

            if (nodes[node.row][node.col] == SHARK) {
                safeLen = node.cost;
                break;
            }

            for (int i = 0; i < 8; i++) {
                int nextRow = node.row + DY[i];
                int nextCol = node.col + DX[i];

                if (!isValid(nextRow, nextCol, nodes.length, nodes[0].length) || VISITS[nextRow][nextCol]) {
                    continue;
                }

                VISITS[nextRow][nextCol] = true;
                _nodes.add(node.next(DX[i], DY[i]));
            }
        }

        initVisits(nodes.length, nodes[0].length);

        return safeLen;
    }

    private static void initVisits(int maxRow, int maxCol) {
        for (int r = 0; r < maxRow; r++) {
            for (int c = 0; c < maxCol; c++) {
                VISITS[r][c] = false;
            }
        }
    }

    private static boolean isValid(int row, int col, int maxRow, int maxCol) {
        return 0 <= row && row < maxRow && 0 <= col && col < maxCol;
    }

    private static class Node {
        private final int row;
        private final int col;
        private final int cost;

        public Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        public Node next(int dx, int dy) {
            return new Node(row + dy, col + dx, cost+1);
        }
    }
}
