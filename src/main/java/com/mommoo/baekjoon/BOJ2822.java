package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ2822 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Score> top5Scores = IntStream.range(0, 8)
                                          .mapToObj(idx -> new Score(idx + 1, parseValue(reader)))
                                          .sorted((s1, s2) -> s2.value - s1.value)
                                          .limit(5)
                                          .collect(Collectors.toList());

        System.out.println(top5Scores.stream()
                                     .mapToInt(score -> score.value)
                                     .sum());

        System.out.println(top5Scores.stream()
                                     .sorted(Comparator.comparingInt(score -> score.position))
                                     .map(score -> score.position)
                                     .map(String::valueOf)
                                     .collect(Collectors.joining(" ")));
    }

    private static Integer parseValue(BufferedReader reader) {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return null;
        }
    }

    private static final class Score {
        private final int position;
        private final int value;

        public Score(int position, int value) {
            this.position = position;
            this.value = value;
        }
    }
}
