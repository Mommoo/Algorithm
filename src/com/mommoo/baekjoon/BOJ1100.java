package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class BOJ1100 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int result = IntStream.range(0, 8)
                              .map(idx -> computeObjectInWhiteArea(idx, readChessLine(reader)))
                              .sum();

        System.out.println(result);
    }

    private static String readChessLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException io) {
            return "";
        }
    }

    private static int computeObjectInWhiteArea(int index, String chessLine) {
        boolean isFirstWhite = index % 2 == 0;
        int beginIndex = isFirstWhite ? 0 : 1;

        return (int) IntStream.iterate(beginIndex, idx -> idx + 2)
                              .limit(4)
                              .map(chessLine::charAt)
                              .filter(charInt -> charInt == 'F')
                              .count();
    }
}
