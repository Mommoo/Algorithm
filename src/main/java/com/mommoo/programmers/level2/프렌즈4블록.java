package com.mommoo.programmers.level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class 프렌즈4블록 {
    public static void main(String[] args) {
        System.out.println(new 프렌즈4블록().solution(4, 5, new String[]{
                "CCBDE",
                "AAADE",
                "AAABF",
                "CCBBF"
        }));
        System.out.println(new 프렌즈4블록().solution(6, 6, new String[]{
                "TTTANT",
                "RRFACC",
                "RRRFCC",
                "TRRRAA",
                "TTMMMF",
                "TMMTTJ"
        }));
    }

    public int solution(int m, int n, String[] board) {
        char[][] boards = createBoard(m, n, board);
        int answer = 0;
        while (true) {
            Set<RowAndCol> rowAndCols = computeNeedRemoveBlocks(boards);
            if (rowAndCols.isEmpty()) {
                break;
            }

            answer += rowAndCols.size();
            dropUnits(boards, rowAndCols);
        }
        return answer;
    }

    private static char[][] createBoard(int m, int n, String[] board) {
        char[][] boards = new char[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                boards[row][col] = board[row].charAt(col);
            }
        }
        return boards;
    }

    private static Set<RowAndCol> computeNeedRemoveBlocks(char[][] board) {
        Set<RowAndCol> needRemoveBlocks = new HashSet<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                RowAndCol rowAndCol = new RowAndCol(row, col);
                List<RowAndCol> blocks = rowAndCol.getBlocks();
                if (isRemoveBlocks(board, blocks)) {
                    needRemoveBlocks.addAll(blocks);
                }
            }
        }

        return needRemoveBlocks;
    }

    private static char getUnit(char[][] board, RowAndCol rowAndCol) {
        return board[rowAndCol.row][rowAndCol.col];
    }

    private static boolean isRemoveBlocks(char[][] board, List<RowAndCol> blocks) {
        char c = getUnit(board, blocks.get(0));
        return blocks.stream()
                     .filter(block -> block.isValid(board.length, board[0].length))
                     .filter(block -> getUnit(board, block) != '_')
                     .filter(block -> getUnit(board, block) == c)
                     .count() == 4;
    }

    private static void dropUnits(char[][] board, Set<RowAndCol> removeUnits) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                RowAndCol rowAndCol = new RowAndCol(row, col);
                if (removeUnits.contains(rowAndCol) && row > 0) {
                    dropUnitsAtRow(board, rowAndCol);
                }
            }
        }
    }

    private static void dropUnitsAtRow(char[][] board, RowAndCol rowAndCol) {
        int toRow = rowAndCol.row;
        int col = rowAndCol.col;
        while (toRow > 0) {
            char topUnit = board[toRow -1][col];
            board[toRow -1][col] = '_';
            board[toRow][col] = topUnit;
            toRow--;
        }
    }

    private static class RowAndCol {
        private final int row;
        private final int col;

        public RowAndCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        private RowAndCol moveRight() {
            return new RowAndCol(this.row, this.col + 1);
        }

        private RowAndCol moveBottom() {
            return new RowAndCol(this.row + 1, this.col);
        }

        private RowAndCol moveRightBottom() {
            return new RowAndCol(this.row + 1, this.col + 1);
        }

        private List<RowAndCol> getBlocks() {
            return Arrays.asList(this, moveRight(), moveBottom(), moveRightBottom());
        }

        private boolean isValid(int maxRow, int maxCol) {
            return this.row < maxRow && this.col < maxCol;
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

        @Override
        public String toString() {
            return "RowAndCol{" +
                   "row=" + row +
                   ", col=" + col +
                   '}';
        }
    }
}
