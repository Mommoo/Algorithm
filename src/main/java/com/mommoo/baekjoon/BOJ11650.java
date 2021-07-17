package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ11650 {
    public static void main(String[] args) throws Exception {
        new InputData()
                .lines
                .stream()
                .map(Coordinate::from)
                .sorted()
                .forEach(System.out::println);
    }

    private static class InputData {
        private final List<String> lines;

        public InputData() throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(reader.readLine());

            this.lines = new ArrayList<>(N);

            while (N-- > 0) {
                this.lines.add(reader.readLine());
            }
        }
    }

    private static class Coordinate implements Comparable<Coordinate> {
        private final int x;
        private final int y;

        private Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Coordinate from(String coordinate) {
            String[] elements = coordinate.split(" ");
            return new Coordinate(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]));
        }

        @Override
        public int compareTo(Coordinate o) {
            if (x == o.x) {
                return y - o.y;
            }

            return x - o.x;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}
