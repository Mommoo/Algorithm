package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10708 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int playerCount = Integer.parseInt(br.readLine());
        int gameCount = Integer.parseInt(br.readLine());
        StringTokenizer targetPlayerOrders = new StringTokenizer(br.readLine());
        int[] scores = new int[playerCount];
        while (gameCount -- > 0) {
            int target = Integer.parseInt(targetPlayerOrders.nextToken()) - 1;
            int notCorrectAnswerCount = 0;
            StringTokenizer answers = new StringTokenizer(br.readLine());
            for (int i = 0 ; i < playerCount; i++) {
                int answer = Integer.parseInt(answers.nextToken()) - 1;
                if (i == target) {
                    continue;
                }

                if (answer != target) {
                    notCorrectAnswerCount++;
                } else {
                    scores[i] += 1;
                }
            }

            scores[target] += notCorrectAnswerCount + 1;
        }

        for (int score: scores) {
            bw.write(String.valueOf(score));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
