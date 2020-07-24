package com.mommoo.baekjoon.no10275;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(reader.readLine());
        while (TC-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            long a = Long.parseLong(tokenizer.nextToken());
            long b = Long.parseLong(tokenizer.nextToken());

            long findGold = findNeedToMinGold(Math.min(a, b));
            long nextGold = (long) Math.pow(2, n);
            int days = 0;
            while (nextGold != findGold) {
                nextGold = nextGold / 2L;
                days++;
            }

            writer.write(String.valueOf(days));
            writer.newLine();
        }

        reader.close();
        writer.close();
    }

    private static long findNeedToMinGold(double minGold) {
        int exp = 0;
        while (minGold % Math.pow(2, exp) == 0) {
            exp++;
        }

        double needToMinGold = Math.pow(2, exp - 1);
        double remainGold = minGold - needToMinGold;
        if (remainGold != 0L && !isTwoSquareValue(remainGold)) {
            return (long) Math.min(needToMinGold, findNeedToMinGold(remainGold));
        }
        return (long) needToMinGold;
    }

    private static boolean isTwoSquareValue(double value) {
        int exp = 1;
        while (value > Math.pow(2, exp)) {
            exp++;
        }

        return value == Math.pow(2, exp);
    }
}
