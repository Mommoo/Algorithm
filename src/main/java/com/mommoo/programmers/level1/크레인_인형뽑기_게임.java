package com.mommoo.programmers.level1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class 크레인_인형뽑기_게임 {
    public int solution(int[][] board, int[] moves) {
        int actual = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int move: moves) {
            int value = popValue(board, move - 1);
            if (value == 0) {
                continue;
            }

            Integer integerV = value;
            if (!stack.isEmpty() && Objects.equals(stack.peek(), integerV)) {
                stack.pop();
                actual += 2;
            } else {
                stack.push(integerV);
            }
        }

        return actual;
    }

    private static int popValue(int[][] board, int col) {
        for (int row = 0 ; row < board.length; row++) {
            int value = board[row][col];
            if (value == 0) {
                continue;
            }

            board[row][col] = 0;
            return value;
        }

        return 0;
    }
}
