package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2908 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        ConstNumber firstNum = new ConstNumber(tokenizer.nextToken());
        ConstNumber secondNum = new ConstNumber(tokenizer.nextToken());

        ConstNumber bigNumber = firstNum.isBiggerThan(secondNum) ? firstNum : secondNum;
        System.out.println(bigNumber.toString());
    }

    private static class ConstNumber {
        private final int reverseNumber;

        public ConstNumber(String number) {
            this.reverseNumber = Integer.parseInt(getReverse(number));
        }

        private static String getReverse(String string) {
            StringBuilder builder = new StringBuilder();
            for (int idx = string.length() - 1 ; idx >= 0 ; idx--) {
                builder.append(string.charAt(idx));
            }
            return builder.toString();
        }

        private boolean isBiggerThan(ConstNumber other) {
            return this.reverseNumber > other.reverseNumber;
        }

        @Override
        public String toString() {
            return String.valueOf(reverseNumber);
        }
    }
}
