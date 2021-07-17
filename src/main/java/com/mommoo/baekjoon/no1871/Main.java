package com.mommoo.baekjoon.no1871;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static final String NICE = "nice";
    private static final String NOT_NICE = "not nice";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(reader.readLine());
        while (TC-- > 0) {
            CarNumber carNumber = CarNumber.from(reader.readLine());
            int value = carNumber.computeValue();
            writer.write(value <= 100 ? NICE : NOT_NICE);
            writer.newLine();
        }

        reader.close();
        writer.close();
    }

    private static class CarNumber {
        private final String prefix;
        private final String suffix;

        public CarNumber(String prefix, String suffix) {
            this.prefix = prefix;
            this.suffix = suffix;
        }

        public static CarNumber from(String carNumber) {
            return new CarNumber(carNumber.substring(0, 3), carNumber.substring(4));
        }

        public int computeValue() {
            int prefixValueNumber = (int)(computeAlphabetNumber(prefix.charAt(0)) * Math.pow(26, 2)
                                          + computeAlphabetNumber(prefix.charAt(1)) * Math.pow(26, 1)
                                          + computeAlphabetNumber(prefix.charAt(2)) * Math.pow(26, 0));

            int suffixValueNumber = Integer.parseInt(suffix);

            return Math.abs(suffixValueNumber - prefixValueNumber);
        }

        private static int computeAlphabetNumber(char alphabet) {
            return alphabet - 'A';
        }
    }
}
