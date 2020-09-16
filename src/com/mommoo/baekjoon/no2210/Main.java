package com.mommoo.baekjoon.no2210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};
    private static final Set<String> NUMBERS = new HashSet<>();
    private static char[][] NODES;

    public static void main(String[] args) throws IOException {
        NODES = createNodes();
        for (int row = 0 ; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                dfs(row, col, NODES[row][col] + "");
            }
        }
        System.out.println(NUMBERS.size());
    }

    private static char[][] createNodes() throws IOException {
        char[][] nodes = new char[5][5];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0 ; i < 5; i ++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < 5; col++) {
                nodes[i][col] = tokenizer.nextToken().charAt(0);
            }
        }
        reader.close();
        return nodes;
    }

    private static void dfs(int row, int col, String number) {
        if (number.length() == 6) {
            NUMBERS.add(number);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            if (0<= nextRow && nextRow < 5 && 0 <= nextCol && nextCol < 5) {
                dfs(nextRow, nextCol, number + NODES[nextRow][nextCol]);
            }
        }
    }
}
