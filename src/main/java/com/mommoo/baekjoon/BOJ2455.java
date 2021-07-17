package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2455 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalTakenPersonCount = 0;
        int maxTakenPersonCount = 0;
        for (int i = 0; i < 4; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int takeOffPersonCount = Integer.parseInt(tokenizer.nextToken());
            int takeOnPersonCount = Integer.parseInt(tokenizer.nextToken());
            totalTakenPersonCount -= takeOffPersonCount;
            totalTakenPersonCount += takeOnPersonCount;
            maxTakenPersonCount = Math.max(maxTakenPersonCount, totalTakenPersonCount);

            if (maxTakenPersonCount == 10_000) {
                break;
            }
        }

        System.out.println(maxTakenPersonCount);
    }
}
