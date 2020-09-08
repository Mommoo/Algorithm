package com.mommoo.baekjoon.no4358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> woodCounters = createWoodCounters();
        int totalWoodCount = computeTotalWoodCount(woodCounters);
        List<Wood> woods = createWoods(woodCounters, totalWoodCount);
        Collections.sort(woods);
        printWoodRatios(woods, totalWoodCount);
    }

    private static Map<String, Integer> createWoodCounters() throws IOException {
        Map<String, Integer> woodCounters = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = reader.readLine()) != null) {
            int count = woodCounters.getOrDefault(line, 0);
            woodCounters.put(line, count + 1);
        }

        reader.close();

        return woodCounters;
    }

    private static List<Wood> createWoods(Map<String, Integer> woodCounters, int totalWoodCount) {
        List<Wood> woods = new ArrayList<>(totalWoodCount + 2);
        for(Map.Entry<String, Integer> entry: woodCounters.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            Wood wood = new Wood(name, count);
            woods.add(wood);
        }
        return woods;
    }

    private static int computeTotalWoodCount(Map<String, Integer> woodCounters) {
        int totalWoodCount = 0;
        for (int woodCount: woodCounters.values()) {
            totalWoodCount += woodCount;
        }
        return totalWoodCount;
    }

    private static void printWoodRatios(List<Wood> woods, int totalWoodCount) {
        StringBuilder builder = new StringBuilder();
        for (Wood wood: woods) {
            builder.append(wood.computeWoodRatio(totalWoodCount))
                   .append('\n');
        }
        System.out.println(builder);
    }

    private static class Wood implements Comparable<Wood> {
        private final String name;
        private final int count;

        public Wood(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String computeWoodRatio(int totalWood) {
            float fTotalWood = (float) totalWood;
            float fCount = (float) count;

            return name + " " + String.format("%.4f", fCount * 100 / fTotalWood);
        }

        @Override
        public int compareTo(Wood o) {
            return this.name.compareTo(o.name);
        }
    }
}
