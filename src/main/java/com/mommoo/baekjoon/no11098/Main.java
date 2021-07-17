package com.mommoo.baekjoon.no11098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        while (N-- > 0) {
            Player[] players = createPlayers(reader);
            String maxPricePlayerName = findMaxPricePlayerName(players);
            BUILDER.append(maxPricePlayerName).append('\n');
        }
        System.out.println(BUILDER);
    }

    private static Player[] createPlayers(BufferedReader reader) throws IOException {
        int playerCount = Integer.parseInt(reader.readLine());
        Player[] players = new Player[playerCount];
        for (int i = 0 ; i < playerCount; i++) {
            players[i] = Player.from(reader.readLine());
        }
        return players;
    }

    private static String findMaxPricePlayerName(Player[] players) {
        Player maxPricePlayer = players[0];

        for (Player player : players) {
            if (player.price > maxPricePlayer.price) {
                maxPricePlayer = player;
            }
        }

        return maxPricePlayer.name;
    }

    private static class Player {
        private final int price;
        private final String name;

        public Player(int price, String name) {
            this.price = price;
            this.name = name;
        }

        public static Player from(String playerString) {
            StringTokenizer tokenizer = new StringTokenizer(playerString);

            return new Player(Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken());
        }
    }
}
