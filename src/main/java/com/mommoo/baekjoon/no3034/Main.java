package com.mommoo.baekjoon.no3034;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int W = Integer.parseInt(tokenizer.nextToken());
        int H = Integer.parseInt(tokenizer.nextToken());

        int maxLenPowTwo = (int)Math.pow(W, 2) + (int)Math.pow(H, 2);
        StringBuilder builder = new StringBuilder();
        while (N -- > 0) {
            int len = Integer.parseInt(br.readLine());
            builder.append(Math.pow(len, 2) <= maxLenPowTwo ? "DA" : "NE")
                   .append("\n");
        }

        System.out.println(builder);
    }
}
