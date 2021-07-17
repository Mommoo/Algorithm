package com.mommoo.baekjoon.no8958;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final char CORRECT = 'O';
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        while (TC -- > 0) {
            String quizAnswers = br.readLine();
            int score = computeScore(quizAnswers);
            appendResult(score);
        }

        System.out.println(BUILDER);
    }

    private static int computeScore(String quizAnswers) {
        int score = 0;
        int accNumber = 0;

        for (int i = 0; i < quizAnswers.length(); i++) {
            char c = quizAnswers.charAt(i);

            if (c == CORRECT) {
                accNumber++;
            } else {
                accNumber=0;
            }

            score += accNumber;
        }

        return score;
    }

    private static void appendResult(int score) {
        BUILDER.append(score)
               .append("\n");
    }
}
