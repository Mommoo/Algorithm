package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ5212 {
    private static final char SEA = '.';
    private static final char LAND = 'X';
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int rawSize = Integer.parseInt(tokenizer.nextToken());
        int colSize = Integer.parseInt(tokenizer.nextToken());

        char[][] map = new char[rawSize][colSize];
        char[][] resultMap = new char[rawSize][colSize];

        for(int raw = 0; raw < rawSize; raw++) {
            String mapRawLine = br.readLine();
            for (int col = 0; col < colSize; col++) {
                map[raw][col] = mapRawLine.charAt(col);
                resultMap[raw][col] = mapRawLine.charAt(col);
            }
        }

        for(int raw = 0; raw < rawSize; raw++) {
            for (int col = 0; col < colSize; col++) {
                if (map[raw][col] == LAND && isShouldErased(raw, col, map)) {
                    resultMap[raw][col] = SEA;
                }
            }
        }

        int beginRaw = Integer.MAX_VALUE;
        int endRaw = -1;
        int beginCol = Integer.MAX_VALUE;
        int endCol = -1;

        for(int raw = 0; raw < rawSize; raw++) {
            for (int col = 0; col < colSize; col++) {
                if (resultMap[raw][col] == LAND) {
                    beginRaw = Math.min(beginRaw, raw);
                    endRaw = Math.max(endRaw, raw);
                    beginCol = Math.min(beginCol, col);
                    endCol = Math.max(endCol, col);
                }
            }
        }

        for(int raw = beginRaw; raw <= endRaw; raw++) {
            for (int col = beginCol; col <= endCol; col++) {
                bw.write(resultMap[raw][col]);
            }
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    private static boolean isShouldErased(int raw, int col, char[][] map) {
        int seaCount = 0;
        for (int i = 0; i < 4; i++) {
            int nextRaw = raw - dy[i];
            int nextCol = col - dx[i];

            if (0 <= nextRaw && nextRaw < map.length && 0 <= nextCol && nextCol < map[0].length && map[nextRaw][nextCol] == LAND) {
                continue;
            }

            seaCount++;
        }

        return seaCount > 2;
    }
}
