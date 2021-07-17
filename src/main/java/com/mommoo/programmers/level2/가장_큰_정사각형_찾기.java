package com.mommoo.programmers.level2;

public class 가장_큰_정사각형_찾기 {
    public static void main(String[] args) {
        int[][] board1 = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 1, 0}
        };
        System.out.println(new 가장_큰_정사각형_찾기().solution(board1));
        int[][] board2 = {
                {0, 0, 0, 1},
                {1, 1, 1, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };
        System.out.println(new 가장_큰_정사각형_찾기().solution(board2));
    }

    public int solution(int[][] b) {
        Board board = new Board(b);

        for (int row = 0; row < b.length; row ++){
            for (int col = 0; col < b[row].length; col++) {
                board.checkSquare(row, col);
            }
        }

        return board.getMaxArea();
    }

    private static class Board {
        private final int[][] elements;
        private int maxWidth = 0;

        public Board(int[][] elements) {
            this.elements = elements;
        }

        private int getElement(int row, int col) {
            if (row < 0 || row >= elements.length || col < 0 || col >= elements[0].length) {
                return 0;
            }
            return elements[row][col];
        }

        private int computeMinWidthInAroundSquare(int row, int col) {
            int leftElementWidth = getElement(row, col - 1);
            int topElementWidth = getElement(row - 1, col);
            int leftTopElementWidth = getElement(row - 1, col - 1);

            return Math.min(leftElementWidth, Math.min(topElementWidth, leftTopElementWidth));
        }

        private void updateWidth(int row, int col, int width) {
            int currentWidth = getElement(row, col);
            if (currentWidth == 0) {
                return;
            }

            int nextCurrentWidth = width + 1;
            elements[row][col] = nextCurrentWidth;
            this.maxWidth = Math.max(this.maxWidth, nextCurrentWidth);
        }

        public void checkSquare(int row, int col) {
            int minWidthInAroundSquare = computeMinWidthInAroundSquare(row, col);
            updateWidth(row, col, minWidthInAroundSquare);
        }

        public int getMaxArea() {
            return maxWidth * maxWidth;
        }
    }
}
