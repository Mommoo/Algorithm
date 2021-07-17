package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ5533 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int playerCount = Integer.parseInt(br.readLine());
        List<Player> players = new ArrayList<>();
        while (playerCount-- > 0) {
            players.add(Player.from(br.readLine()));
        }

        Set<Integer> scores = new HashSet<>();
        Set<Integer> duplicatedScores = new HashSet<>();
        for (int i = 0 ; i < 3; i++) {
            scores.clear();
            duplicatedScores.clear();
            for (Player player: players) {
                if (scores.contains(player.peekScore())) {
                    duplicatedScores.add(player.peekScore());
                } else {
                    scores.add(player.peekScore());
                }
            }

            for (Player player: players) {
                if (duplicatedScores.contains(player.peekScore())) {
                    player.discardScore();
                } else {
                    player.obtainScore();
                }
            }
        }

        for (Player player: players) {
            bw.write(String.valueOf(player.totalScore));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static class Player {
        private final LinkedList<Integer> scores;
        private int totalScore;

        private Player(LinkedList<Integer> scores) {
            this.scores = scores;
        }

        public static Player from(String scoreString) {
            LinkedList<Integer> scores = new LinkedList<>();
            StringTokenizer tokenizer = new StringTokenizer(scoreString);
            while (tokenizer.hasMoreTokens()) {
                scores.add(Integer.parseInt(tokenizer.nextToken()));
            }
            return new Player(scores);
        }

        public Integer peekScore() {
            return scores.getFirst();
        }

        public void obtainScore() {
            totalScore += scores.poll();
        }

        public void discardScore() {
            scores.removeFirst();
        }
    }
}
