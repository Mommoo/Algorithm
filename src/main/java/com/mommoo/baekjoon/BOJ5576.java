package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ5576 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(sumOfTop3In10Score(reader) + " " + sumOfTop3In10Score(reader));
    }

    private static int sumOfTop3In10Score(BufferedReader reader) throws IOException {
        return build10Scores(reader).stream()
                                    .sorted(Collections.reverseOrder())
                                    .mapToInt(Integer::intValue)
                                    .limit(3)
                                    .sum();
    }

    private static List<Integer> build10Scores(BufferedReader reader) throws IOException {
        List<Integer> topScores = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int score = Integer.parseInt(reader.readLine());
            topScores.add(score);
        }

        return topScores;
    }
}
