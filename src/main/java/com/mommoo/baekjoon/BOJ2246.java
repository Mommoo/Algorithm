package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2246 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int condoCount = Integer.parseInt(reader.readLine());

        int properCondoCount = 0;
        LinkedList<Condo> condos = createCondos(condoCount, reader);
        while (condoCount -- > 0) {
            Condo condo = condos.poll();
            properCondoCount += isProperCondoThanOthers(condo, condos) ? 1 : 0;
            condos.offer(condo);
        }

        System.out.println(properCondoCount);
    }

    private static LinkedList<Condo> createCondos(int condoCount, BufferedReader reader) throws IOException {
        LinkedList<Condo> condos = new LinkedList<>();
        while (condoCount -- > 0) {
            condos.add(Condo.from(reader.readLine()));
        }
        return condos;
    }

    private static boolean isProperCondoThanOthers(Condo condo, List<Condo> condos) {
        return condos.stream()
                     .allMatch(condo::isProperConditionAs);
    }

    private static class Condo {
        private final int distanceFromSea;
        private final int cost;

        private Condo(int distanceFromSea, int cost) {
            this.distanceFromSea = distanceFromSea;
            this.cost = cost;
        }

        public static Condo from(String condoInfo) {
            StringTokenizer tokenizer = new StringTokenizer(condoInfo);
            return new Condo(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        public boolean isProperConditionAs(Condo other) {
            if (this.distanceFromSea > other.distanceFromSea) {
                if (this.cost < other.cost) {
                    return true;
                } else {
                    return false;
                }
            }

            if (this.cost > other.cost) {
                if (this.distanceFromSea < other.distanceFromSea) {
                    return true;
                } else {
                    return false;
                }
            }

            return true;
        }

        @Override
        public String toString() {
            return "Condo{" +
                   "distanceFromSea=" + distanceFromSea +
                   ", cost=" + cost +
                   '}';
        }
    }
}