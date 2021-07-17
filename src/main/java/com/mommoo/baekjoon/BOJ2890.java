package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int rawSize = Integer.parseInt(stringTokenizer.nextToken());
        int colSize = Integer.parseInt(stringTokenizer.nextToken());
        int maxDistanceFromFinal = colSize - 2 - 3;

        List<Kayak> kayaks = new ArrayList<>();
        for (int raw = 0; raw < rawSize; raw++) {
            String line = br.readLine();

            int distanceFromFinal = 0;
            for (int col = colSize - 2; col >= 1; col--) {
                if (distanceFromFinal > maxDistanceFromFinal) {
                    break;
                }

                char c = line.charAt(col);
                if (c != '.') {
                    kayaks.add(new Kayak(c - '0', distanceFromFinal));
                    break;
                }
                distanceFromFinal++;
            }
        }

        kayaks.sort(Comparator.comparingInt(kayak -> kayak.distanceFromFinal));

        int rank = 1;
        kayaks.get(0).rank = 1;
        for (int i = 1; i < kayaks.size() ; i++) {
            Kayak previous = kayaks.get(i - 1);
            Kayak current = kayaks.get(i);

            if (current.distanceFromFinal != previous.distanceFromFinal) {
                rank++;
            }

            current.rank = rank;
        }

        kayaks.sort(Comparator.comparingInt(kayak -> kayak.order));
        for (Kayak kayak : kayaks) {
            bw.write(String.valueOf(kayak.rank));
            bw.newLine();
        }

        bw.close();
        br.close();
    }

    private static class Kayak {
        private final int order;
        private final int distanceFromFinal;
        private int rank;

        public Kayak(int order, int distanceFromFinal) {
            this.order = order;
            this.distanceFromFinal = distanceFromFinal;
        }
    }
}
