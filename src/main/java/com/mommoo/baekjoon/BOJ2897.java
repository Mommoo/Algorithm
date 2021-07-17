package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ2897 {
    private static final char BUILDING = '#';
    private static final char CAR = 'X';
    private static final char LAND = '.';
    private static final int[] dx = {0, 1, 0, 1};
    private static final int[] dy = {0, 0, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int rawSize = Integer.parseInt(tokenizer.nextToken());
        int colSize = Integer.parseInt(tokenizer.nextToken());

        char[][] parkingMap = new char[2][colSize];
        int[] answers = new int[5];

        for (int raw = 0; raw < rawSize; raw++) {
            String parkingLine = br.readLine();
            for (int col = 0; col < colSize; col++) {
                parkingMap[0][col] = parkingMap[1][col];
                parkingMap[1][col] = parkingLine.charAt(col);
            }

            if (raw == 0) {
                continue;
            }

            search2X2Parking(answers, parkingMap);
        }

        for (int answer: answers) {
            bw.write(String.valueOf(answer));
            bw.newLine();
        }

        bw.close();
        br.close();
    }

    public static void search2X2Parking(int[] answers, char[][] map) {
        int len = map[0].length - 1;
        int raw = 0;

        for (int col = 0; col < len; col++) {
            int landCount = 0;
            boolean isContainBuilding = false;

            for (int directionIndex = 0; directionIndex < dx.length; directionIndex++) {
                int nextRaw = raw + dy[directionIndex];
                int nextCol = col + dx[directionIndex];
                if (map[nextRaw][nextCol] == BUILDING) {
                    isContainBuilding = true;
                    break;
                } else if (map[nextRaw][nextCol] == CAR) {
                    landCount++;
                }
            }

            if(!isContainBuilding) {
                answers[landCount]++;
            }
        }


    }
}
