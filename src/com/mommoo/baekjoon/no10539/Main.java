package com.mommoo.baekjoon.no10539;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numCount = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        if (numCount == 1) {
            System.out.println(tokenizer.nextToken());
            return;
        }

        int[] BNums = new int[numCount];

        for (int i = 0; i < numCount; i++) {
            BNums[i] = Integer.parseInt(tokenizer.nextToken());
        }

        StringBuilder builder = new StringBuilder(String.valueOf(BNums[0]));
        for (int i = 0; i < numCount - 1; i++) {
            int len = i + 1;
            int aNum = (BNums[i+1] * (len + 1)) - (BNums[i] * len);
            builder.append(" ").append(aNum);
        }

        System.out.println(builder);
    }
}
