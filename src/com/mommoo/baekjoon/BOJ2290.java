package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2290 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(tokenizer.nextToken());
        String nums = tokenizer.nextToken();

        StringBuilder builder = new StringBuilder();
        int rawCount = 2 * S + 3;
        for (int raw = 0 ; raw < rawCount; raw++) {
            for (int col = 0; col < nums.length(); col++) {
                if (col != 0) {
                    builder.append(' ');
                }
                char c = nums.charAt(col);
                appendLine(builder, S, c - '0', raw);
            }
            builder.append('\n');
        }

        System.out.println(builder);
    }

    public static void appendLine(StringBuilder builder, int S, int targetNumber, int lineNumber) {
        int lineLength = 2 + S;
        int middleIndex = ((2 * S + 3) / 2);

        if (lineNumber == 0) {
            switch(targetNumber) {
                case 1: case 4:
                    appendBlankLine(builder, lineLength);
                    break;
                default:
                    appendLineAtMiddle(builder, lineLength);
            }
        } else if (0 < lineNumber && lineNumber < middleIndex) {
            switch(targetNumber) {
                case 1: case 2: case 3: case 7:
                    appendRightRawLine(builder, lineLength);
                    break;
                case 0: case 4: case 8: case 9:
                    appendBothRawLine(builder, lineLength);
                    break;
                default:
                    appendLeftRawLine(builder, lineLength);

            }
        } else if (lineNumber == middleIndex) {
            switch(targetNumber) {
                case 0: case 1: case 7:
                    appendBlankLine(builder, lineLength);
                    break;
                default:
                    appendLineAtMiddle(builder, lineLength);
            }
        } else if (middleIndex < lineNumber && lineNumber < 2 * S + 2) {
            switch (targetNumber) {
                case 2:
                    appendLeftRawLine(builder, lineLength);
                    break;
                case 0: case 6: case 8:
                    appendBothRawLine(builder, lineLength);
                    break;
                default:
                    appendRightRawLine(builder, lineLength);
            }
        } else if (lineNumber == 2 * S + 2) {
            switch(targetNumber) {
                case 1: case 4: case 7:
                    appendBlankLine(builder, lineLength);
                    break;
                default:
                    appendLineAtMiddle(builder, lineLength);
            }
        }
    }

    private static void appendBlankLine(StringBuilder builder, int lineLength) {
        while (lineLength -- > 0) {
            builder.append(' ');
        }
    }

    private static void appendLineAtMiddle(StringBuilder builder, int lineLength) {
        for (int i = 0 ; i < lineLength; i++) {
            if (i == 0 || i == lineLength - 1) {
                builder.append(' ');
            } else {
                builder.append('-');
            }
        }
    }

    private static void appendBothRawLine(StringBuilder builder, int lineLength) {
        for (int i = 0; i < lineLength; i++) {
            if (i == 0 || i == lineLength - 1) {
                builder.append('|');
            } else {
                builder.append(' ');
            }
        }
    }

    private static void appendLeftRawLine(StringBuilder builder, int lineLength) {
        builder.append('|');
        for (int i = 0 ; i < lineLength - 1; i++) {
            builder.append(' ');
        }
    }

    private static void appendRightRawLine(StringBuilder builder, int lineLength) {
        for (int i = 0 ; i < lineLength - 1; i++) {
            builder.append(' ');
        }
        builder.append('|');
    }
}
