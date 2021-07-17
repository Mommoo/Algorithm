package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2799 {
    private static final int[] windowCounts = new int[5];

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int floorCount = Integer.parseInt(tokenizer.nextToken());
        int windowCountInFloor = Integer.parseInt(tokenizer.nextToken());
        while (floorCount-- > 0) {
            countWindowsInRaw(windowCountInFloor, reader);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(windowCounts[i]).append(" ");
        }
        System.out.println(builder);
    }

    private static void countWindowsInRaw(int windowCountInFloor, BufferedReader reader) throws IOException {
        reader.readLine(); // drop top frame
        boolean[] windowCountCheck = new boolean[windowCountInFloor];
        for (int lineCount = 0; lineCount < 4; lineCount++) {
            String line = reader.readLine();
            for (int windowPosition = 0; windowPosition < windowCountInFloor; windowPosition++) {
                int beginBlindIndex = windowPosition * 5 + 1;
                if (!windowCountCheck[windowPosition] && line.charAt(beginBlindIndex) == '.') {
                    windowCounts[lineCount]++;
                    windowCountCheck[windowPosition] = true;
                }

                if (lineCount == 3 && line.charAt(beginBlindIndex) == '*') {
                    windowCounts[4]++;
                }
            }
        }
    }
}
