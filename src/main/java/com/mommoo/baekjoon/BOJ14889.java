package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ14889 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalCount = Integer.parseInt(br.readLine());
        int[][] scores = createScores(totalCount, br);
        int minSubScore = Integer.MAX_VALUE;
        TeamDecider teamDecider = new TeamDecider(totalCount);
        while (true) {
            Teams teams = teamDecider.findNextTeamsOrNull();
            if (teams == null) {
                break;
            }
            minSubScore = Math.min(minSubScore, teams.computeSubScores(scores));
            if (minSubScore == 0) {
                break;
            }
        }

        System.out.println(minSubScore);
    }

    private static int[][] createScores(int totalCount, BufferedReader br) throws IOException {
        int[][] scores = new int[totalCount][totalCount];
        for (int row = 0 ; row < totalCount; row++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int col = 0;
            while (tokenizer.hasMoreTokens()) {
                scores[row][col++] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        return scores;
    }

    private static class TeamDecider {
        private final int totalCount;
        private final Stack<Team> teams = new Stack<>();
        private final boolean[] pickChecks;

        public TeamDecider(int totalCount) {
            this.totalCount = totalCount;
            pickChecks = new boolean[totalCount];
            teams.push(Team.first());
        }

        private boolean hasNotVisitCondition(Team team) {
            return team.values.get(0) <= totalCount / 2;
        }

        public Teams findNextTeamsOrNull() {
            while (!teams.isEmpty()) {
                Team team = teams.pop();
                if (team.equalsMemberCount(totalCount / 2) && hasNotVisitCondition(team)) {
                    return decideTeam(team.values);
                }

                if (team.beginIndex == totalCount - 1) {
                    continue;
                }

                teams.push(team.nextWithAddMember());
                teams.push(team.nextWithoutAddMember());
            }

            return null;
        }

        private Teams decideTeam(List<Integer> foundTeam) {
            for (int memberOrder: foundTeam) {
                int memberIndex = memberOrder - 1;
                pickChecks[memberIndex] = true;
            }

            List<Integer> memberOrders = new LinkedList<>();
            for (int i = 0 ; i < pickChecks.length; i++) {
                if (pickChecks[i]) {
                    continue;
                }

                int memberOrder = i + 1;
                memberOrders.add(memberOrder);
            }

            Teams teams = new Teams(foundTeam, memberOrders);
            Arrays.fill(pickChecks, false);
            return teams;
        }
    }

    private static class Team {
        private final int beginIndex;
        private final List<Integer> values;

        public Team(int beginIndex, List<Integer> values) {
            this.beginIndex = beginIndex;
            this.values = values;
        }

        public static Team first() {
            return new Team(0, new LinkedList<>());
        }

        public Team nextWithAddMember() {
            List<Integer> newValues = new LinkedList<>(values);
            newValues.add(beginIndex + 1);
            return new Team(beginIndex + 1, newValues);
        }

        public Team nextWithoutAddMember() {
            return new Team(beginIndex + 1, new LinkedList<>(this.values));
        }

        public boolean equalsMemberCount(int memberCount) {
            return this.values.size() == memberCount;
        }
    }

    private static class Teams {
        private final List<Integer> start;
        private final List<Integer> link;

        public Teams(List<Integer> start, List<Integer> link) {
            this.start = start;
            this.link = link;
        }

        private static int computeScore(int[][] scores, List<Integer> team) {
            int totalScore = 0;
            int row = 0;
            for (int rowTeam : team) {
                int col = 0;
                for (int colTeam: team) {
                    if (row == col) {
                        col++;
                        continue;
                    }
                    int targetRow = rowTeam - 1;
                    int targetCol = colTeam - 1;

                    totalScore += scores[targetRow][targetCol];
                    col++;
                }
                row ++;
            }
            return totalScore;
        }

        public int computeSubScores(int[][] scores) {
            return Math.abs(computeScore(scores, start) - computeScore(scores, link));
        }

        @Override
        public String toString() {
            return "Teams{" +
                   "start=" + start +
                   ", link=" + link +
                   '}';
        }
    }
}
