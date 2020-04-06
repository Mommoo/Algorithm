package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BOJ2798 {
    private static List<Integer> SCORES;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int cardCount = Integer.parseInt(tokenizer.nextToken());
        int blackjackScore = Integer.parseInt(tokenizer.nextToken());
        SCORES = Stream.of(reader.readLine().split(" "))
                                     .map(Integer::parseInt)
                                     .collect(Collectors.toList());

        int maxScore = findMaxScore( 0, 3, 0, 0, blackjackScore);
        System.out.println(maxScore);
    }

    private static int findMaxScore(int nextIndex, int choiceCount, int currentScore, int maxScore, int blackjackScore) {
        if (choiceCount == 0) {
            if (currentScore <= blackjackScore && currentScore > maxScore) {
                return currentScore;
            }
            return maxScore;
        }

        int nextMaxScore = maxScore;
        for (int i = nextIndex; i < SCORES.size(); i++) {
            int score = SCORES.get(i);
            int findMaxScore = findMaxScore(i + 1, choiceCount-1, currentScore + score, nextMaxScore, blackjackScore);
            nextMaxScore = Math.max(maxScore, findMaxScore);
        }

        return nextMaxScore;
    }
}
