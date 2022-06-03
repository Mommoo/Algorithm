package com.mommoo.programmers.level1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class 크레인_인형뽑기_게임Test {
    @DisplayName("테스트")
    @ParameterizedTest
    @MethodSource("provideTestSuite")
    void test(int[][] board, int[] moves, int expectResult) {
        // given
        // when
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
            print(board);
            System.out.println(stack);
            System.out.println("-----------------------------");
        }

        // then
        assertThat(actual).isSameAs(expectResult);
    }

    private int popValue(int[][] board, int col) {
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

    private static void print(int[][] board) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                System.out.print(board[row][col]+" ");
            }
            System.out.println();
        }
    }

    private static Stream<Arguments> provideTestSuite() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {0, 0, 0, 0, 0},
                                {0, 0, 1, 0, 3},
                                {0, 2, 5, 0, 1},
                                {4, 2, 4, 4, 2},
                                {3, 5, 1, 3, 1},
                        },
                        new int[]{
                                1,5,3,5,1,2,1,4
                        },
                        4
                )
        );
    }
}