package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1051 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer rowAndColTokenizer = new StringTokenizer(br.readLine());
        int maxRow = Integer.parseInt(rowAndColTokenizer.nextToken());
        int maxCol = Integer.parseInt(rowAndColTokenizer.nextToken());

        int maxSize = 1;

        char[][] map = createMap(maxRow, maxCol, br);
        for (int row = 1; row < maxRow; row++) {
            for (int col = 1; col < maxCol; col++) {
                int maxLen = Math.min(row, col);
                for (int len = 1; len < maxLen + 1; len++) {
                    int topLeft = map[row - len][col - len];
                    int topRight = map[row -len][col];
                    int bottomLeft = map[row][col - len];
                    int bottomRight = map[row][col];
                    if (!isEqualsAll(topLeft, topRight, bottomLeft, bottomRight)) {
                        continue;
                    }

                    maxSize = Math.max(maxSize, (int)Math.pow(len + 1, 2));
                }
            }
        }

        System.out.println(maxSize);
    }

    private static char[][] createMap(int maxRow, int maxCol, BufferedReader br) throws IOException {
        char[][] map = new char[maxRow][maxCol];

        for (int row = 0; row < maxRow; row++) {
            String mapLine = br.readLine();
            for (int col = 0; col < maxCol; col++) {
                map[row][col] = mapLine.charAt(col);
            }
        }

        return map;
    }

    private static boolean isEqualsAll(int... values) {
        for (int i = 1; i < values.length; i++) {
            if (values[0] != values[i]) {
                return false;
            }
        }

        return true;
    }
}
