package com.mommoo.baekjoon.no11650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Coordinate[] coordinates = new Coordinate[N];
        for (int i = 0 ; i < N; i++) {
            coordinates[i] = Coordinate.from(br.readLine());
        }

        Arrays.sort(coordinates);
        for (Coordinate coordinate: coordinates) {
            System.out.println(coordinate.x + " " + coordinate.y);
        }
    }

    private static class Coordinate implements Comparable<Coordinate> {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Coordinate from(String string) {
            StringTokenizer tokenizer = new StringTokenizer(string);
            return new Coordinate(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        @Override
        public int compareTo(Coordinate o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }
}
