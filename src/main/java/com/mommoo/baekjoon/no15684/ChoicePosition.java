package com.mommoo.baekjoon.no15684;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChoicePosition {
    private static final int NODE_SIZE = 3;
    private static final int MARK_COUNT = 2;
    private static final char[][] NODES = new char[NODE_SIZE][NODE_SIZE];

    public static void main(String[] args) {
        init(NODES);
        DFS(0, -1, new ArrayList<>());
    }

    private static void init(char[][] nodes) {
        for (int row = 0; row < NODE_SIZE; row++) {
            for (int col = 0; col < NODE_SIZE; col++) {
                nodes[row][col] = '.';
            }
        }
    }

    private static void print(char[][] nodes) {
        System.out.println("=================================================================");
        for (int row = 0; row < NODE_SIZE; row++) {
            for (int col = 0; col < NODE_SIZE; col++) {
                System.out.print(nodes[row][col]);
            }
            System.out.println();
        }
        System.out.println("=================================================================");
    }

    private static char[][] createNodes() {
        char[][] nodes = new char[NODE_SIZE][NODE_SIZE];
        init(nodes);
        return nodes;
    }

    private static void mark(LinkedPosition position, char[][] nodes) {
        do {
            nodes[position.row][position.col] = 'X';
            position = position.prev;
        } while (position != null);
    }

    private static void BFS() {
        Queue<LinkedPosition> positions = new LinkedList<>();
        positions.add(LinkedPosition.first(0, 0));
        while (!positions.isEmpty()) {
            LinkedPosition position = positions.poll();

            if (position.size == MARK_COUNT) {
                char[][] nodes = createNodes();
                mark(position, nodes);
                print(nodes);
                continue;
            }

            LinkedPosition markedNextPosition = position.next(true);
            if (markedNextPosition != null) {
                positions.offer(markedNextPosition);
            }

            LinkedPosition unMarkedNextPosition = position.next(false);
            if (unMarkedNextPosition != null) {
                positions.offer(unMarkedNextPosition);
            }

            LinkedPosition nextFirst = position.nextFirst();
            if (nextFirst != null) {
                positions.offer(nextFirst);
            }
        }
    }

    private static void DFS(int row, int col, List<Position> positions) {
        if (positions.size() == MARK_COUNT) {
            for (Position p: positions) {
                NODES[p.row][p.col] = 'X';
            }
            print(NODES);
            for (Position p: positions) {
                NODES[p.row][p.col] = '.';
            }
            return;
        }

        int nextCol = col + 1;
        int nextRow = row;

        if (nextCol == NODE_SIZE) {
            nextRow += 1;
            nextCol = 0;
        }

        if (nextRow == NODE_SIZE) {
            return;
        }

        positions.add(new Position(nextRow, nextCol));
        DFS(nextRow, nextCol, positions);
        positions.remove(positions.size() - 1);

        DFS(nextRow, nextCol, positions);
    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class LinkedPosition {
        private final LinkedPosition prev;
        private final int size;
        private final int row;
        private final int col;

        public LinkedPosition(LinkedPosition prev, int size, int row, int col) {
            this.prev = prev;
            this.size = size;
            this.row = row;
            this.col = col;
        }

        public static LinkedPosition first(int row, int col) {
            return new LinkedPosition(null, 1, row, col);
        }

        public LinkedPosition nextFirst() {
            int nextCol = col + 1;
            int nextRow = row;

            if (nextCol == NODE_SIZE) {
                nextRow += 1;
                nextCol = 0;
            }

            if (nextRow == NODE_SIZE) {
                return null;
            }

            return LinkedPosition.first(nextRow, nextCol);
        }

        public LinkedPosition next(boolean mark) {
            int nextCol = col + 1;
            int nextRow = row;

            if (nextCol == NODE_SIZE) {
                nextRow += 1;
                nextCol = 0;
            }

            if (nextRow == NODE_SIZE) {
                return null;
            }

            if (mark) {
                return new LinkedPosition(this, this.size + 1, nextRow, nextCol);
            }

            return new LinkedPosition(this.prev, this.size, nextRow, nextCol);
        }
    }
}
