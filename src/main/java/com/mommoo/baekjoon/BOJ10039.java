package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BOJ10039 {
    public static void main(String[] args) throws Exception {
        int scoreAverage = (int) inputScores().stream()
                                              .mapToInt(score -> Math.max(score, 40))
                                              .average()
                                              .orElseThrow(() -> new NoSuchElementException("No value present"));

        System.out.println(scoreAverage);
    }

    private static List<Integer> inputScores() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int N = 5;

            List<Integer> scores = new ArrayList<>();
            while (N-- > 0) {
                scores.add(Integer.parseInt(reader.readLine()));
            }

            return scores;
        }
    }
}
