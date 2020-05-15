package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ3023 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int rawSize = Integer.parseInt(tokenizer.nextToken());
        int colSize = Integer.parseInt(tokenizer.nextToken());
        int maxRaw = rawSize * 2;
        int maxCol = colSize * 2;
        char[][] cardPixels = new char[maxRaw][maxCol];

        for (int raw = 0; raw < rawSize; raw++) {
            String cardRawLine = reader.readLine();
            for (int col = 0; col < colSize; col++) {
                char targetPixel = cardRawLine.charAt(col);
                cardPixels[raw][col] = targetPixel;
                cardPixels[raw][maxCol - col - 1] = targetPixel;
                cardPixels[maxRaw - raw - 1][col] = targetPixel;
                cardPixels[maxRaw - raw - 1][maxCol - col - 1] = targetPixel;
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        int errorRaw = Integer.parseInt(tokenizer.nextToken());
        int errorCol = Integer.parseInt(tokenizer.nextToken());

        cardPixels[errorRaw - 1][errorCol - 1] = cardPixels[errorRaw - 1][errorCol - 1] == '#' ? '.' : '#';

        for (char[] pixelLine : cardPixels) {
            for (char pixel: pixelLine) {
                bw.write(pixel);
            }
            bw.write("\n");
        }

        bw.close();
        reader.close();
    }
}
