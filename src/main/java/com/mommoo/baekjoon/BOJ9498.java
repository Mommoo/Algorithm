package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class BOJ9498 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int score = Integer.parseInt(reader.readLine());
        ExamResult[] results = {
                new ExamResult(90, 100, 'A'),
                new ExamResult(80, 89, 'B'),
                new ExamResult(70, 79, 'C'),
                new ExamResult(60, 69, 'D'),
                new ExamResult(0, 59, 'F')
        };

        char code = Arrays.stream(results)
                          .filter(examResult -> examResult.isContain(score))
                          .findFirst()
                          .map(ExamResult::getCode)
                          .orElseThrow(NoSuchElementException::new);

        System.out.println(code);
    }

    private static class ExamResult {
        private final int minScore;
        private final int maxScore;
        private final char code;

        public ExamResult(int minScore, int maxScore, char code) {
            this.minScore = minScore;
            this.maxScore = maxScore;
            this.code = code;
        }

        public boolean isContain(int score) {
            return minScore <= score && score <= maxScore;
        }

        public char getCode() {
            return code;
        }
    }
}
