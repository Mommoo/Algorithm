package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ3085 {
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};


    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        boolean[][] visits = new boolean[N][N];
        char[][] board = createBoard(N, reader);
        int maxCount = 1;

        for (int raw = 0; raw < N; raw++) {
            for (int col = 0; col < N; col++) {
                if (visits[raw][col]) {
                    continue;
                }

                visits[raw][col] = true;

                for (int i = 0 ; i < 4; i++) {
                    int nextRaw = raw + dy[i];
                    int nextCol = col + dx[i];
                    if (isInvalid(N, nextRaw, nextCol) || visits[nextRaw][nextCol]) {
                        continue;
                    }

                    swap(board, raw, col, nextRaw, nextCol);
                    maxCount = Math.max(maxCount, computeMaxLineLength(board, raw, col));
                    maxCount = Math.max(maxCount, computeMaxLineLength(board, nextRaw, nextCol));
                    swap(board, raw, col, nextRaw, nextCol);
                }
            }
        }

        System.out.println(maxCount);
    }

    private static char[][] createBoard(int size, BufferedReader reader) throws IOException {
        char[][] board = new char[size][size];
        for (int raw = 0; raw < size; raw++) {
            String line = reader.readLine();
            for (int col = 0; col < size; col++) {
                board[raw][col] = line.charAt(col);
            }
        }
        return board;
    }

    private static boolean isInvalid(int N, int raw, int col) {
        return !(0 <= raw && raw < N && 0 <= col && col < N);
    }

    private static void swap(char[][] board, int fromRaw, int fromCol, int toRaw, int toCol) {
        char tmp = board[fromRaw][fromCol];
        board[fromRaw][fromCol] = board[toRaw][toCol];
        board[toRaw][toCol] = tmp;
    }

    private static int computeMaxLineLength(char[][] board, int raw, int col) {
        int maxCount = 1;
        int beginRaw = 0;
        int count = 1;
        while (beginRaw < board.length - 1) {
            if (board[beginRaw][col] == board[beginRaw + 1][col]) {
                count++;
            } else {
                maxCount = Math.max(count, maxCount);
                count = 1;
            }
            beginRaw++;
        }
        maxCount = Math.max(count, maxCount);

        int beginCol = 0;
        count = 1;
        while (beginCol < board.length - 1) {
            if (board[raw][beginCol] == board[raw][beginCol + 1]) {
                count++;
            } else {
                maxCount = Math.max(count, maxCount);
                count = 1;
            }
            beginCol++;
        }
        maxCount = Math.max(count, maxCount);

        return maxCount;
    }
}
