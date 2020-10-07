package com.mommoo.baekjoon.no2953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Score[] scores = new Score[5];
        for (int i = 0; i < 5; i++) {
            scores[i] = Score.from(new StringTokenizer(reader.readLine()), i + 1);
        }

        Score maxScore = scores[0];
        for (int i = 1; i < 5; i++) {
            if (scores[i].biggerThan(maxScore)) {
                maxScore = scores[i];
            }
        }

        System.out.println(maxScore);
    }

    private static class Score {
        private final int value;
        private final int index;

        public static Score from(StringTokenizer tokenizer, int index) {
            int sum = 0;
            while (tokenizer.hasMoreTokens()) {
                sum += tokenizer.nextToken().charAt(0) - '0';
            }

            return new Score(sum, index);
        }

        private Score(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public boolean biggerThan(Score score) {
            return this.value > score.value;
        }

        @Override
        public String toString() {
            return this.index + " " + this.value;
        }
    }
}
