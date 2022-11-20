package com.mommoo.programmers.dev._2021;

public class Solution2 {
    private static final int RIGHT = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;

    private static final int[] DIRECTION_X = {1, 0, -1, 0};
    private static final int[] DIRECTION_Y = {0, 1, 0, -1};

    public static void main(String[] args) {
        new Solution2().solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6},{5, 1, 6, 3}});
    }


    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] numTable = createNumTable(rows, columns);
        int[] answer = new int[queries.length];

        int index = -1;
        for (int[] query: queries) {
            answer[++index] = rotate(numTable, query);
        }

        return answer;
    }

    private static int[][] createNumTable(int rowCount, int colCount) {
        int[][] nums = new int[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                nums[row][col] = (colCount * row + col) + 1;
            }
        }

        return nums;
    }

    private static int rotate(int[][] numTable, int[] query) {
        int curDirection = RIGHT;
        int curRow = query[0] - 1;
        int curCol = query[1] - 1;
        int curNum = numTable[curRow][curCol];
        int beginNum = curNum;

        int minNum = curNum;

        do {
            int nextRow = curRow + DIRECTION_Y[curDirection];
            int nextCol = curCol + DIRECTION_X[curDirection];

            if (!isIn(nextRow, nextCol, query)) {
                ++curDirection;
                continue;
            }

            int tmp = numTable[nextRow][nextCol];
            numTable[nextRow][nextCol] = curNum;
            curNum = tmp;

            minNum = Math.min(curNum, minNum);

            curRow = nextRow;
            curCol = nextCol;

        } while (curNum != beginNum);
        printTable(numTable);
        return minNum;
    }

    private static boolean isIn(int row, int col, int[] query) {
        return query[0] - 1 <= row && row < query[2] && query[1] - 1 <= col && col < query[3];
    }

    private static void printTable(int[][] numTable) {
        for (int[] ints : numTable) {
            for (int anInt : ints) {
                if (anInt < 10) {
                    System.out.print(" "+anInt + " ");
                    continue;
                }
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        System.out.println("---------------------------------------------");
    }
}
