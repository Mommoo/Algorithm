package com.mommoo.baekjoon.no4963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {0, 0, -1, -1, 1, 1, 1, -1};
    private static final int[] dy = {1, -1, 1, -1, 1, -1, 0, 0};

    private static final char LAND = '1';
    private static final char SEA = '0';

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        while (true) {
            String size = reader.readLine();
            if ("0 0".equals(size)) {
                break;
            }

            StringTokenizer tokenizer = new StringTokenizer(size);
            int maxCol = Integer.parseInt(tokenizer.nextToken());
            int maxRow = Integer.parseInt(tokenizer.nextToken());
            char[][] nodes = createNodes(maxRow, maxCol, reader);
            builder.append(countLand(maxRow, maxCol, nodes))
                   .append('\n');
        }

        System.out.println(builder);
    }

    private static char[][] createNodes(int maxRow, int maxCol, BufferedReader reader) throws IOException {
        char[][] nodes = new char[maxRow][maxCol];
        for (int row = 0; row < maxRow; row++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < maxCol; col++) {
                nodes[row][col] = tokenizer.nextToken().charAt(0);
            }
        }
        return nodes;
    }

    private static int countLand(int maxRow, int maxCol, char[][] nodes) {
        int landCount = 0;
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                if (nodes[row][col] == LAND) {
                    removeLand(row, col, nodes);
                    landCount++;
                }
            }
        }

        return landCount;
    }

    private static void removeLand(int row, int col, char[][] nodes) {
        if (isInvalid(row, col, nodes.length, nodes[0].length) || nodes[row][col] == SEA) {
            return;
        }

        nodes[row][col] = SEA;
        for (int i = 0; i < 8; i++) {
            removeLand(row + dy[i], col + dx[i], nodes);
        }
    }

    private static boolean isInvalid(int row, int col, int maxRow, int maxCol) {
        return !(0 <= row && row < maxRow && 0 <= col && col < maxCol);
    }
}
