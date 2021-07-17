package com.mommoo.programmers.level3;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class 카드_짝_맞추기 {
    private static final int NONE_CARD = 0;

    private static final int MAX_ROW = 4;
    private static final int MAX_COL = 4;

    private static final int RANDOM_CARD = -1;
    private static final int ENTER_PRESS = 1;

    private static final int[] DX = {0, -1, 0, 1};
    private static final int[] DY = {1, 0, -1, 0};

    private static final boolean[][] VISITS = new boolean[4][4];
    private static final Queue<Node> NODES = new LinkedList<>();

    public static void main(String[] args) {
//        int[][] board = {{1,0,0,3}, {2,0,0,0}, {0,0,0,2}, {3,0,1,0}};
//        System.out.println(new 카드_짝_맞추기().solution(board, 1, 0));
//
        int[][] board2 = {{3,0,0,2}, {0,0,1,0}, {0,1,0,0}, {2,0,0,3}};
        System.out.println(new 카드_짝_맞추기().solution(board2, 0, 1));

//        int[][] board3 = {{3,4,0,2}, {0,4,1,5}, {0,1,0,5}, {2,0,0,3}};
//        System.out.println(new 카드_짝_맞추기().solution(board3, 0, 1));
    }

    private static void print(int[][] board) {
        for (int i = 0 ; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int solution(int[][] board, int r, int c) {
        int totalCardCount = computeCardCount(board);

        int controlCount = 0;
        Position nextPosition = new Position(r, c);

        int findCardNumber = RANDOM_CARD;
        while (totalCardCount != 0) {
            Node cardNode = findNextCardPosition(board, nextPosition, findCardNumber);

            controlCount += cardNode.cost;
            controlCount += ENTER_PRESS;

            totalCardCount--;

            System.out.println("findNode: " + cardNode + ", " + getBoardValue(board, cardNode.position));
            print(board);

            if (totalCardCount % 2 == 0) {
                findCardNumber = RANDOM_CARD;
            } else {
                findCardNumber = getBoardValue(board, cardNode.position);
            }

            nextPosition = cardNode.position;
            removeCard(board, cardNode.position);
        }

        return controlCount;
    }

    private static boolean isInvalid(int row, int col) {
        return !(0 <= row && row < MAX_ROW && 0 <= col && col < MAX_COL);
    }

    private static int getBoardValue(int[][] board, Position position) {
        return board[position.row][position.col];
    }

    private static boolean hasAnyCard(int[][] board, Position position) {
        return getBoardValue(board, position) != NONE_CARD;
    }

    private static boolean hasSpecificCard(int[][] board, Position position, int cardNumber) {
        return getBoardValue(board, position) == cardNumber;
    }

    private static void initVisits() {
        for (int row = 0 ; row < MAX_ROW; row ++) {
            for (int col = 0; col < MAX_COL; col++) {
                VISITS[row][col] = false;
            }
        }
    }

    private static void initNodes() {
        NODES.clear();
    }

    private static void removeCard(int[][] board, Position position) {
        board[position.row][position.col] = NONE_CARD;
    }

    private static int computeCardCount(int[][] board) {
        int cardCount = 0;
        for (int row = 0 ; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                if (board[row][col] != NONE_CARD) {
                    cardCount++;
                }
            }
        }
        return cardCount;
    }

    // cardNumber -1 is random
    private static Node findNextCardPosition(int[][] board, Position begin, int cardNumber) {
        if (hasAnyCard(board, begin)) {
            return new Node(0, begin);
        }

        initVisits();
        initNodes();
        NODES.offer(new Node(0, begin.row, begin.col));

        while (!NODES.isEmpty()) {
            Node curNode = NODES.poll();

            // find any card
            if (cardNumber == RANDOM_CARD && hasAnyCard(board, curNode.position)) {
                return curNode;
            }

            // find specific card
            if (cardNumber != RANDOM_CARD && hasSpecificCard(board, curNode.position, cardNumber)) {
                return curNode;
            }

            // ctrl + move
            offerCtrlMoveNodes(board, curNode);

            // move
            offerMoveNodes(curNode);
        }

        return null;
    }

    private static void offerCtrlMoveNodes(int[][] board, Node curNode) {
        for (int i = 0; i < 4; i++) {
            Position nextPos = computeMoveWithCtrlPosition(board, curNode.position, DY[i], DX[i]);

            // cannot moved or already visit
            if (curNode.position.equals(nextPos) || VISITS[nextPos.row][nextPos.col]) {
                continue;
            }

            VISITS[nextPos.row][nextPos.col] = true;
            NODES.offer(curNode.nextNode(nextPos));
        }
    }

    private static void offerMoveNodes(Node curNode) {
        for (int i = 0; i < 4; i++) {
            int nextRow = curNode.position.row + DY[i];
            int nextCol = curNode.position.col + DX[i];

            if (isInvalid(nextRow, nextCol) || VISITS[nextRow][nextCol]) {
                continue;
            }

            VISITS[nextRow][nextCol] = true;
            NODES.offer(curNode.nextNode(nextRow, nextCol));
        }
    }

    private static Position computeMoveWithCtrlPosition(int[][] board, Position position, int dy, int dx) {
        int nextRow = position.row;
        int nextCol = position.col;

        while (true) {
            nextRow += dy;
            nextCol += dx;

            if (isInvalid(nextRow, nextCol)) {
                //go to last block
                return new Position(nextRow - dy, nextCol - dx);
            }

            if (board[nextRow][nextCol] != NONE_CARD) {
                // find card
                return new Position(nextRow, nextCol);
            }
        }
    }

    private static class Node {
        private final int cost;
        private final Position position;

        public Node(int cost, int row, int col) {
            this.cost = cost;
            this.position = new Position(row, col);
        }

        public Node(int cost, Position position) {
            this.cost = cost;
            this.position = position;
        }

        public Node nextNode(Position position) {
            return new Node(cost + 1, position);
        }

        public Node nextNode(int row, int col) {
            return new Node(cost+ 1, row, col);
        }

        @Override
        public String toString() {
            return "Node{" +
                   "cost=" + cost +
                   ", row=" + position.row +
                   ", col=" + position.col +
                   '}';
        }
    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Position))
                return false;
            Position rowAndCol = (Position) o;
            return row == rowAndCol.row &&
                   col == rowAndCol.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
