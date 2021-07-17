package com.mommoo.baekjoon.no2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int N;
    private static boolean[][] visits;
    private static int[][] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        visits = new boolean[N][N];
        nodes = createNodes(reader);
        reader.close();

        int maxHeight = findMaxHeight();

        int targetHeight = 0;
        int maxAreaCount = 1;
        while (targetHeight++ < maxHeight) {
            maxAreaCount = Math.max(maxAreaCount, computeSafeArea(targetHeight));
            clearVisits();
        }

        System.out.println(maxAreaCount);
    }

    private static int[][] createNodes(BufferedReader reader) throws IOException {
        int[][] nodes = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < N; col++) {
                nodes[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        return nodes;
    }

    private static int findMaxHeight() {
        int maxHeight = 0;

        for (int row = 0; row < N; row++) {
            for (int col =0; col < N; col++) {
                if (maxHeight < nodes[row][col]) {
                    maxHeight = nodes[row][col];
                }
            }
        }

        return maxHeight;
    }

    private static int computeSafeArea(int height) {
        int safeAreaCount = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int node = nodes[row][col];

                if (node > height && !visits[row][col]) {
                    dfs(row, col, height);
                    safeAreaCount++;
                }
            }
        }

        return safeAreaCount;
    }

    private static void dfs(int row, int col, int height) {
        for (int i = 0 ; i < 4; i++) {
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            boolean isValid = 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N;
            if (isValid && !visits[nextRow][nextCol] && nodes[nextRow][nextCol] > height) {
                visits[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, height);
            }
        }
    }

    private static void clearVisits() {
        for (boolean[] visitLine: visits) {
            Arrays.fill(visitLine, false);
        }
    }
}
