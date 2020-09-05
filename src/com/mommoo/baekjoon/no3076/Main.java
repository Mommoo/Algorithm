package com.mommoo.baekjoon.no3076;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int SQUARE_WIDTH;
    private static int SQUARE_HEIGHT;
    private static int BLOCK_WIDTH;
    private static int BLOCK_HEIGHT;
    private static String WHITE_LINE;
    private static String BLACK_LINE;

    private static final char WHITE = '.';
    private static final char BLACK = 'X';
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        SQUARE_HEIGHT = Integer.parseInt(tokenizer.nextToken());
        SQUARE_WIDTH = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        BLOCK_HEIGHT = Integer.parseInt(tokenizer.nextToken());
        BLOCK_WIDTH = Integer.parseInt(tokenizer.nextToken());

        BLACK_LINE = createLine(BLACK);
        WHITE_LINE = createLine(WHITE);

        fillLines();

        System.out.println(BUILDER);
    }

    private static String createLine(char c) {
        for (int col = 0; col < BLOCK_WIDTH; col++) {
            BUILDER.append(c);
        }

        String line = BUILDER.toString();
        BUILDER.setLength(0);
        return line;
    }

    public static void fillLines() {
        for (int blockRow = 0; blockRow < SQUARE_HEIGHT; blockRow++) {
            if (blockRow % 2 == 0) {
                fillLine(BLACK_LINE, WHITE_LINE);
            } else {
                fillLine(WHITE_LINE, BLACK_LINE);
            }
        }
    }

    public static void fillLine(String firstColor, String secondColor) {
        for (int row = 0; row < BLOCK_HEIGHT; row++) {
            for (int blockCol = 0; blockCol < SQUARE_WIDTH; blockCol++) {
                BUILDER.append(blockCol % 2 == 0? firstColor : secondColor);
            }
            BUILDER.append('\n');
        }
    }
}
