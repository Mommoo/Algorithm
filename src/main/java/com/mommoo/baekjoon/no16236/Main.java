package com.mommoo.baekjoon.no16236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final Comparator<RowAndCol> POSITION_COMPARATOR = (p1, p2) -> {
        if (p1.row == p2.row) {
            return p1.col - p2.col;
        }

        return p1.row - p2.row;
    };
    private static final int[] dx = {0, -1 , 1, 0};
    private static final int[] dy = {1, 0, 0, -1};
    private static final boolean[][] VISITS = new boolean[20][20];

    private static final Queue<Node> NODES = new LinkedList<>();

    private static int SHARK_SIZE = 2;
    private static int EAT_COUNT = 0;
    private static RowAndCol SHARK_POSITION;


    private static int[][] MAP;

    private static int TIME = 0;

    public static void main(String[] args) throws Exception {
        MAP = inputMap();
        SHARK_POSITION = findShark();
        while (true) {
            Node fishNode = findFish();

            if (fishNode == null) {
                System.out.println(TIME);
                break;
            }

            eatFish(fishNode);
            clearVisits();
        }
    }

    private static int[][] inputMap() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] map = new int[size][size];

        for (int row = 0 ; row < size; row++) {
            StringTokenizer mapLine = new StringTokenizer(br.readLine());
            for (int col = 0; col < size; col++) {
                map[row][col] = Integer.parseInt(mapLine.nextToken());
            }
        }

        br.close();
        return map;
    }

    private static RowAndCol findShark() {
        for (int row = 0; row < MAP.length; row ++) {
            for (int col = 0; col < MAP[row].length; col++) {
                if (MAP[row][col] == 9) {
                    return new RowAndCol(row, col);
                }
            }
        }

        return null;
    }

    private static Node findFish() {
        Set<RowAndCol> findPositions = new HashSet<>();
        offerNode(Node.first(SHARK_POSITION.row, SHARK_POSITION.col));

        int findNodeCost = -1;
        while (!NODES.isEmpty()) {
            Node node = NODES.poll();

            if (isEatableFish(node.row, node.col)) {
                findNodeCost = node.cost;
                findPositions.add(new RowAndCol(node.row, node.col));

                offerIfExistEatableFishNodeInSameNodeCost(node.cost, findPositions);
                continue;
            }

            for (int i = 0 ; i < 4; i++) {
                int nextRow = node.row + dy[i];
                int nextCol = node.col + dx[i];

                if (!canPass(nextRow, nextCol) || VISITS[nextRow][nextCol]) {
                    continue;
                }

                offerNode(node.next(nextRow, nextCol));
            }
        }

        return findHighPriorityPosition(findPositions, findNodeCost);
    }

    private static boolean isSharkPosition(int row, int col) {
        return SHARK_POSITION.row == row && SHARK_POSITION.col == col;
    }

    private static void offerNode(Node curNode) {
        NODES.add(curNode);
        VISITS[curNode.row][curNode.col] = true;
    }

    private static void offerIfExistEatableFishNodeInSameNodeCost(int nodeCost, Set<RowAndCol> results) {
        while (!NODES.isEmpty()) {
            Node node = NODES.poll();
            if (node.cost == nodeCost && isEatableFish(node.row, node.col)) {
                results.add(new RowAndCol(node.row, node.col));
            }
        }
    }

    private static Node findHighPriorityPosition(Set<RowAndCol> positions, int nodeCost) {
        if (positions.isEmpty()) {
            return null;
        }

        List<RowAndCol> lPositions = new ArrayList<>(positions);
        lPositions.sort(POSITION_COMPARATOR);

        return Node.of(lPositions.get(0), nodeCost);
    }

    private static void eatFish(Node fishNode) {
        MAP[SHARK_POSITION.row][SHARK_POSITION.col] = 0;
        MAP[fishNode.row][fishNode.col] = 9;

        EAT_COUNT++;
        if (EAT_COUNT == SHARK_SIZE) {
            SHARK_SIZE++;
            EAT_COUNT = 0;
        }

        TIME += fishNode.cost;

        SHARK_POSITION = new RowAndCol(fishNode.row, fishNode.col);
    }

    private static boolean isEatableFish(int row, int col) {
        if (isSharkPosition(row, col)) {
            return false;
        }
        int nodeValue = MAP[row][col];
        return nodeValue != 0 && nodeValue < SHARK_SIZE;
    }

    private static boolean canPass(int row, int col) {
        if (row < 0 || row >= MAP.length || col < 0 || col >= MAP.length) {
            return false;
        }

        int nodeValue = MAP[row][col];
        return nodeValue == 0 || nodeValue <= SHARK_SIZE;
    }

    private static void clearVisits() {
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                VISITS[row][col] = false;
            }
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
        private final int cost;

        public static Node first(int row, int col) {
            return new Node(row, col, 0);
        }

        public Node next(int row, int col) {
            return new Node(row, col, cost + 1);
        }

        public Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        public static Node of(RowAndCol rowAndCol, int value) {
            return new Node(rowAndCol.row, rowAndCol.col, value);
        }
    }
}
