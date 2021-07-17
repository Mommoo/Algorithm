package com.mommoo.baekjoon.no14464;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int chickenCount = Integer.parseInt(tokenizer.nextToken());
        int cowCount = Integer.parseInt(tokenizer.nextToken());
        int[] chickenTimes = new int[chickenCount];
        for (int i = 0; i < chickenCount; i++) {
            chickenTimes[i] = Integer.parseInt(reader.readLine());
        }

        Cow[] cows = new Cow[cowCount];
        for (int i = 0 ; i < cowCount ; i++) {
            cows[i] = Cow.from(reader.readLine());
        }

        Arrays.sort(cows);
        Arrays.sort(chickenTimes);

        int movedCount = 0;
        for (Cow cow: cows) {
            int foundChickenIndex = findChickenIndex(cow, chickenTimes);
            if (foundChickenIndex == -1) {
                continue;
            }

            chickenTimes[foundChickenIndex] = -1;
            movedCount++;
        }

        System.out.println(movedCount);
    }

    private static int findChickenIndex(Cow cow, int[] chickenTimes) {
        int index = 0;
        for (int chickTime: chickenTimes) {
            if (cow.end < chickTime) {
                return -1;
            }

            if (cow.canMoved(chickTime)) {
                return index;
            }

            index++;
        }

        return -1;
    }

    private static class Cow implements Comparable<Cow> {
        private final int begin;
        private final int end;

        public Cow(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        public static Cow from(String cowString) {
            StringTokenizer tokenizer = new StringTokenizer(cowString);
            int begin = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            return new Cow(begin, end);
        }

        public boolean canMoved(int chickenTime) {
            return begin <= chickenTime && chickenTime <= end;
        }

        @Override
        public int compareTo(Cow o) {
            if (o.end == end) {
                return begin - o.begin;
            }
            return end - o.end;
        }
    }
}
