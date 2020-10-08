package com.mommoo.baekjoon.no16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {0, -1 , 1, 0};
    private static final int[] dy = {1, 0, 0, -1};

    private static int N;
    private static int[][] NODES;
    private static boolean[][] VISITS;

    private static int SHARK_SIZE = 2;
    private static int SHARK_EAT_COUNT;
    private static RowAndCol SHARK_POSITION;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        NODES = createNodes(reader);
        VISITS = new boolean[N][N];
        reader.close();

        int totalTime = 0;
        while (true) {
            int time = eatFish();

            if (time == 0) {
                break;
            }

            growShark();

            totalTime += time;
        }

        System.out.println(totalTime);
    }

    private static int[][] createNodes(BufferedReader reader) throws IOException {
        int[][] nodes = new int[N][N];

        for (int row = 0 ; row < N; row++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < N; col++) {
                nodes[row][col] = tokenizer.nextToken().charAt(0) - '0';

                if (nodes[row][col] == 9) {
                    SHARK_POSITION = new RowAndCol(row, col);
                }
            }
        }

        return nodes;
    }

    private static int eatFish() {
        Node node = bfs(SHARK_POSITION.row, SHARK_POSITION.col);
        System.out.println("target!!");
        System.out.println(node);
        if (node == null) {
            return 0;
        }
        NODES[node.row][node.col] = NODES[SHARK_POSITION.row][SHARK_POSITION.col] =  0;
        SHARK_POSITION = node.toRowAndCol();
        initVisits();
        return node.time;
    }

    private static Node bfs(int row, int col) {
        System.out.println("shark: " + row + " , " + col);
        VISITS[row][col] = true;
        Node first = new Node(row, col, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.add(first);

        while (!queue.isEmpty()) {
            Node node = queue.peek();
//            System.out.println(node);
            int r = node.row;
            int c = node.col;
            int time = node.time;

            if (NODES[r][c] != 0 && NODES[r][c] < SHARK_SIZE) {
                break;
            }

            queue.poll();

            for (int i = 0 ; i < 4; i++) {
                int nextRow = r + dy[i];
                int nextCol = c + dx[i];

                boolean isInvalid = !(0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N);

                if (isInvalid || VISITS[nextRow][nextCol] || SHARK_SIZE < NODES[nextRow][nextCol]) {
                    continue;
                }

                VISITS[nextRow][nextCol] = true;
                queue.offer(new Node(nextRow, nextCol, time+1));
            }
        }

        return findTargetFish(queue);
    }

    private static Node findTargetFish(Queue<Node> nodes) {
        System.out.println("finded!!");
        System.out.println(nodes);

        int row = Integer.MAX_VALUE;
        int col = Integer.MAX_VALUE;
        Node node = null;
        for (Node n : nodes) {
            if (n.row < row || (n.row == row && n.col < col)) {
                node = n;
            }
        }

        return node;
    }

    private static void initVisits() {
        for (int row = 0 ; row < N; row++) {
            Arrays.fill(VISITS[row], false);
        }
    }

    private static void growShark() {
        SHARK_EAT_COUNT++;
        if (SHARK_EAT_COUNT == SHARK_SIZE) {
            SHARK_SIZE++;
            SHARK_EAT_COUNT = 0;
        }
    }

    private static class RowAndCol {
        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "RowAndCol{" +
                   "row=" + row +
                   ", col=" + col +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof RowAndCol))
                return false;
            RowAndCol rowAndCol = (RowAndCol) o;
            return row == rowAndCol.row &&
                   col == rowAndCol.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static class Node {
        private final int row;
        private final int col;
        private final int time;

        public Node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        public RowAndCol toRowAndCol() {
            return new RowAndCol(row, col);
        }

        @Override
        public String toString() {
            return "Node{" +
                   "row=" + row +
                   ", col=" + col +
                   ", time=" + time +
                   '}';
        }
    }
}
