package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10989 {
    public static void main(String[] args) throws IOException {
        int[] mapper = new int[10_001];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberCount = Integer.parseInt(br.readLine());
        while (numberCount -- > 0) {
            mapper[Integer.parseInt(br.readLine())]++;
        }
        br.close();

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < 10_001; i++) {
            for (int j = 0; j < mapper[i]; j++) {
                builder.append(i).append("\n");
            }
        }
        System.out.println(builder);
    }
}
