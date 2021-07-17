package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2790 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] scores = new int[N];

        for (int i = 0 ; i < N ; i++) {
            scores[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(scores);
        int answer = 1;
        int max = scores[N - 1] + 1;
        for (int i = N - 2; i >= 0 ; i--) {
            if (scores[i] + N < max) {
                break;
            }

            answer++;
            max = Math.max(max, scores[i] + N - i);
        }

        System.out.println(answer);
    }
}
