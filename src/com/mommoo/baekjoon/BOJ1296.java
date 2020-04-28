package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ1296 {
    public static void main(String[] args) throws Exception {
        Map<Character, Integer> loveFinder = new HashMap<>();
        List<GirlFriend> girlFriends = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ME = reader.readLine();
        int N = Integer.parseInt(reader.readLine());
        while (N -- > 0) {
            initLoverFinder(loveFinder);
            String girlfriend = reader.readLine();
            String total = ME + girlfriend;

            for (int i = 0; i < total.length(); i++) {
                char c = total.charAt(i);
                loveFinder.computeIfPresent(c, (key, value) -> value + 1);
            }

            int loveScore = computeLoveScore(loveFinder);
            girlFriends.add(new GirlFriend(loveScore, girlfriend));
        }

        Collections.sort(girlFriends);
        System.out.println(girlFriends.get(0).name);
    }

    private static void initLoverFinder(Map<Character, Integer> loveFinder) {
        loveFinder.put('L', 0);
        loveFinder.put('O', 0);
        loveFinder.put('V', 0);
        loveFinder.put('E', 0);
    }

    private static int computeLoveScore(Map<Character, Integer> loveFinder) {
        int L = loveFinder.get('L');
        int O = loveFinder.get('O');
        int V = loveFinder.get('V');
        int E = loveFinder.get('E');
        return ((L+O)*(L+V)*(L+E)*(O+V)*(O+E)*(V+E)) % 100;
    }

    private static class GirlFriend implements Comparable<GirlFriend> {
        private final int loveScore;
        private final String name;

        public GirlFriend(int loveScore, String name) {
            this.loveScore = loveScore;
            this.name = name;
        }

        @Override
        public int compareTo(GirlFriend o) {
            if (this.loveScore == o.loveScore) {
                return name.compareTo(o.name);
            }
            return o.loveScore - this.loveScore;
        }
    }
}
