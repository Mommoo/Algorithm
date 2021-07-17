package com.mommoo.baekjoon.no10703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static char[][] MAP;
    private static final char METEOR = 'X';
    private static final char LAND = '#';
    private static final char AIR = '.';

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int maxRow = Integer.parseInt(tokenizer.nextToken());
        int maxCol = Integer.parseInt(tokenizer.nextToken());
        MAP = createMap(reader, maxRow, maxCol);

        if (isExistMeteorAtOnce()) {
            int maxMoveLen = computeMaxPossibleMoveCount();
            moveDownLand(maxMoveLen);
        }

        System.out.println(computeStringMap());
    }

    private static char[][] createMap(BufferedReader reader, int maxRow, int maxCol) throws IOException {
        char[][] map = new char[maxRow][maxCol];

        for (int row = 0; row < maxRow; row++) {
            String mapLine = reader.readLine();
            for (int col = 0; col < maxCol; col++) {
                map[row][col] = mapLine.charAt(col);
            }
        }

        return map;
    }

    private static boolean isExistMeteorAtOnce() {
        for (char[] mapLine: MAP){
            for (char mapNode: mapLine) {
                if (mapNode == METEOR) {
                    return true;
                }
            }
        }

        return false;
    }

    private static int computeMaxPossibleMoveCount() {
        int maxMoveLen = Integer.MAX_VALUE;
        int maxCol = MAP[0].length;
        for (int col = 0 ; col < maxCol; col++) {
            int lastMeteorRow = computeLastMeteorRow(col);
            int firstLandRow = computeFirstLandRow(col);
            if (lastMeteorRow == -1) {
                continue;
            }

            if (firstLandRow == -1) {
                firstLandRow = MAP.length;
            }

            maxMoveLen = Math.min(maxMoveLen, firstLandRow - lastMeteorRow - 1);
        }
        return maxMoveLen;
    }

    private static int computeLastMeteorRow(int col) {
        for (int row = MAP.length - 1 ; row >= 0; row--) {
            if (MAP[row][col] == METEOR) {
                return row;
            }
        }
        return -1;
    }

    private static int computeFirstLandRow(int col) {
        for (int row = 0; row < MAP.length; row++) {
            if (MAP[row][col] == LAND) {
                return row;
            }
        }
        return -1;
    }

    private static void moveDownLand(int moveLen) {
        int maxCol = MAP[0].length;
        for (int col = 0; col < maxCol; col++) {
            int lastMeteorRow = computeLastMeteorRow(col);
            if (lastMeteorRow == -1) {
                continue;
            }

            for (int row = lastMeteorRow; row >= 0; row--) {
                MAP[row + moveLen][col] = MAP[row][col];
                if (MAP[row][col] == METEOR) {
                    MAP[row][col] = AIR;
                }
            }
        }
    }

    private static String computeStringMap() {
        StringBuilder builder = new StringBuilder();
        for (char[] mapLine: MAP){
            for (char mapNode: mapLine) {
                builder.append(mapNode);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
