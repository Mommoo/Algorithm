package com.mommoo.baekjoon.no3035;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, ZR, ZC;
    private static char[][] RESULT;
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        ZR = Integer.parseInt(tokenizer.nextToken());
        ZC = Integer.parseInt(tokenizer.nextToken());
        RESULT = new char[R * ZR][C * ZC];

        for (int row = 0 ; row < R; row++) {
            String letters = reader.readLine();
            for (int col = 0; col < C; col++) {
                char letter = letters.charAt(col);
                zoom(letter, row, col);
            }
        }

        for (int row = 0 ; row < RESULT.length; row++) {
            for (int col = 0 ; col < RESULT[row].length; col++) {
                BUILDER.append(RESULT[row][col]);
            }

            BUILDER.append("\n");
        }

        System.out.println(BUILDER);
    }

    private static void zoom(char letter, int r, int c) {
        r = r * ZR;
        c = c * ZC;
        for (int row = 0; row < ZR; row++) {
            for (int col = 0; col < ZC; col++) {
                RESULT[r + row][c + col] = letter;
            }
        }
    }
}
