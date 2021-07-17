package com.mommoo.baekjoon.no2999;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = getInput();
        int R = findMaxR(input.length());
        int C = input.length() / R;
        char[][] chars = createCharMetrics(R, C, input);
        System.out.println(deserialize(chars));
    }

    private static String getInput() throws Exception {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return reader.readLine();
        }
    }

    private static int findMaxR(int stringLen) {
        int maxR = 1;
        int accR = 1;

        while (accR <= (stringLen / accR)) {
            if (stringLen % accR == 0) {
                maxR = accR;
            }

            accR++;
        }

        return maxR;
    }

    private static char[][] createCharMetrics(int maxRow, int maxCol, String string) {
        char[][] chars = new char[maxRow][maxCol];
        for (int col = 0; col < maxCol; col++) {
            for (int row = 0; row < maxRow; row++) {
                int position = col * maxRow + row;
                chars[row][col] = string.charAt(position);
            }
        }

        return chars;
    }

    private static String deserialize(char[][] charMetrics) {
        StringBuilder builder = new StringBuilder();
        for (char[] chars : charMetrics) {
            builder.append(chars);
        }

        return builder.toString();
    }
}
