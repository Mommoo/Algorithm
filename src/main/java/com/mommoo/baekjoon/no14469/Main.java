package com.mommoo.baekjoon.no14469;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Cow> cows = orderedCows(reader);
        int totalCost = cows.get(0).computeEnterTime();
        for (int i = 1 ; i < cows.size(); i++) {
            Cow cow = cows.get(i);
            if (totalCost < cow.arrivalTime) {
                totalCost = cow.computeEnterTime();
            } else {
                totalCost += cow.cost;
            }
        }

        System.out.println(totalCost);
    }

    private static List<Cow> orderedCows(BufferedReader reader) throws IOException {
        int size = Integer.parseInt(reader.readLine());

        List<Cow> cows = new ArrayList<>();
        while (size -- > 0) {
            cows.add(Cow.from(reader.readLine()));
        }
        Collections.sort(cows);

        return cows;
    }

    private static class Cow implements Comparable<Cow>{
        private final int arrivalTime;
        private final int cost;

        public Cow(int arrivalTime, int cost) {
            this.arrivalTime = arrivalTime;
            this.cost = cost;
        }

        public static Cow from(String cowString) {
            StringTokenizer tokenizer = new StringTokenizer(cowString);
            return new Cow(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        public int computeEnterTime() {
            return arrivalTime + cost;
        }

        @Override
        public int compareTo(Cow o) {
            return arrivalTime - o.arrivalTime;
        }
    }
}
