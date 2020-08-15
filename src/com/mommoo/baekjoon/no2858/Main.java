package com.mommoo.baekjoon.no2858;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R,B;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        B = Integer.parseInt(tokenizer.nextToken());

        for (int row = 1; row <= B; row++) {
            if (B % row != 0) {
                continue;
            }

            int col = B / row;
            int totalRedCount = 4 + row * 2 + col * 2;

            if (totalRedCount == R) {
                int L = Math.max((col + 2), (row + 2));
                int W = Math.min((col + 2), (row + 2));
                System.out.println(L + " " + W);
                break;
            }
        }
    }
}
