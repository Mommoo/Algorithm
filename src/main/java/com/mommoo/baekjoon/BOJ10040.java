package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10040 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int gameCount = Integer.parseInt(tokenizer.nextToken());
        int jurorCount = Integer.parseInt(tokenizer.nextToken());

        int[] scores = new int[gameCount];
        int[] gameCosts = new int[gameCount];

        for (int i = 0 ; i < gameCount; i++) {
            gameCosts[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < jurorCount ; i++) {
            int investmentCost = Integer.parseInt(br.readLine());
            for (int j = 0; j < gameCount; j++) {
                if (gameCosts[j] <= investmentCost) {
                    scores[j] += 1;
                    break;
                }
            }
        }

        int maxIndex = 0;
        for (int i = 0 ; i < gameCount; i++) {
            if (scores[i] > scores[maxIndex]) {
                maxIndex = i;
            }
        }

        bw.write(String.valueOf(maxIndex + 1));
        bw.newLine();

        bw.close();
        br.close();
    }
}
