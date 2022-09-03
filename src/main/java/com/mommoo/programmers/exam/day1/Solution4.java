package com.mommoo.programmers.exam.day1;

import java.util.*;

public class Solution4 {
    private HashMap<Integer, List<int[]>> ROW_CACHED = new HashMap<>();
    private HashMap<Integer, List<int[]>> COL_CACHED = new HashMap<>();

    public int solution(int[][] beginning, int[][] target) {
        return count(beginning, target, 0, new PriorityQueue<>());
    }

    private boolean isVisitRow(int row, int[][] array) {
        if (!ROW_CACHED.containsKey(row)) {
            return false;
        }

        int[] cols = new int[array.length];
        for (int col = 0 ; col < array.length; col++) {
            cols[col] = array[row][col];
        }

        for (int[] line: ROW_CACHED.get(row)) {
            if (Arrays.equals(line, cols)) {
                return true;
            }
        }

        return false;
    }

    private boolean isVisitCol(int col, int[][] array) {
        if (!COL_CACHED.containsKey(col)) {
            return false;
        }

        for (int[] line: ROW_CACHED.get(col)) {
            if (Arrays.equals(line, array[col])) {
                return true;
            }
        }

        return false;
    }

    private static boolean reverseByRow(int row, int[][] arrays) {
        for (int col = 0; col < arrays[row].length; col++) {
            if (arrays[row][col] == 0) {
                arrays[row][col] = 1;
            } else {
                arrays[row][col] = 0;
            }
        }

        return false;
    }

    private static boolean reverseByCol(int col, int[][] arrays) {

        return false;
    }

    private static int count(int[][] next, int[][] target, int count, Queue<Integer> numbers) {
        if (Arrays.deepEquals(next, target)) {
            return count;
        }

        for (int row = 0; row < next.length; row++) {
            if (reverseByRow(row, next)) {
                int result = count(next, target, count + 1, numbers);
                if (result != -1) {
                    numbers.add(result);
                }

                // 원복
                reverseByRow(row, next);
                count -= 1;
            }
        }

        for (int col = 0; col < next[0].length; col++) {
            if (reverseByCol(col, next)) {
                int result = count(next, target, count + 1, numbers);
                if (result != -1) {
                    numbers.add(result);
                }

                // 원복
                reverseByCol(col, next);
                count -= 1;
            }
        }

        if (numbers.isEmpty()) {
            return -1;
        }

        return numbers.poll();
    }
}
