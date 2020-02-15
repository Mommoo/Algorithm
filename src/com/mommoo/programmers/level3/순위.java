package com.mommoo.programmers.level3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 순위 {
    public static void main(String[] args) {
        System.out.println(new 순위().solution(5, new int[][]{
                new int[]{4, 3},
                new int[]{4, 2},
                new int[]{3, 2},
                new int[]{1, 2},
                new int[]{2, 5}
        }));
    }

    public int solution(int n, int[][] results) {
        GameResults gameResults = GameResults.from(results);
        gameResults.update();
        return gameResults.countRankComputableGameResult(n);
    }

    private static class GameResults {
        private final Map<Integer, GameResult> playerResults = new HashMap<>();

        private static GameResults from(int[][] results) {
            GameResults gameResults = new GameResults();
            for (int[] result : results) {
                int winnerPlayer = result[0];
                int losePlayer = result[1];
                gameResults.addWinner(winnerPlayer, losePlayer);
                gameResults.addLoser(winnerPlayer, losePlayer);
            }
            return gameResults;
        }

        private GameResult getGameResult(Integer player) {
            return playerResults.computeIfAbsent(player, key -> new GameResult());
        }

        private void addWinner(Integer winnerPlayer, Integer loserPlayer) {
            getGameResult(winnerPlayer).addWin(loserPlayer);
        }

        private void addLoser(Integer winnerPlayer, Integer loserPlayer) {
            getGameResult(loserPlayer).addLose(winnerPlayer);
        }

        private void update() {
            for (GameResult playerGameResult : playerResults.values()) {
                notifyToWinner(playerGameResult);
                notifyToLoser(playerGameResult);
            }
        }

        private void notifyToWinner(GameResult playerGameResult) {
            for (Integer winner : playerGameResult.loses) {
                getGameResult(winner).addWins(playerGameResult);
            }
        }

        private void notifyToLoser(GameResult playerGameResult) {
            for (Integer loser : playerGameResult.wins) {
                getGameResult(loser).addLoses(playerGameResult);
            }
        }

        private int countRankComputableGameResult(int totalPlayerCount) {
            return (int) playerResults.values()
                                      .stream()
                                      .filter(gameResult -> gameResult.canComputeRank(totalPlayerCount))
                                      .count();
        }
    }

    private static class GameResult {
        private static final int SELF_COUNT = 1;

        private final Set<Integer> wins = new HashSet<>();
        private final Set<Integer> loses = new HashSet<>();

        public void addWin(Integer player) {
            wins.add(player);
        }

        public void addLose(Integer player) {
            loses.add(player);
        }

        public void addLoses(GameResult gameResult) {
            this.loses.addAll(gameResult.loses);
        }

        public void addWins(GameResult gameResult) {
            this.wins.addAll(gameResult.wins);
        }

        public boolean canComputeRank(int totalPlayerCount) {
            return wins.size() + loses.size() == totalPlayerCount - SELF_COUNT;
        }
    }
}
