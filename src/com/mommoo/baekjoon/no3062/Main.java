package com.mommoo.baekjoon.no3062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();
    private static final String YES = "YES";
    private static final String NO = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        while (N-- > 0) {
            int number = Integer.parseInt(reader.readLine());
            int reverseNumber = reverseNumber(number);
            int numberSum = number + reverseNumber;
            BUILDER.append(isSymmetric(numberSum) ? YES : NO)
                   .append("\n");
        }
        System.out.println(BUILDER);
    }

    private static int reverseNumber(int number) {
        String sNumber = Integer.toString(number);
        StringBuilder builder = new StringBuilder();
        for (int i = sNumber.length() - 1; i >= 0; i--) {
            builder.append(sNumber.charAt(i));
        }

        return Integer.parseInt(builder.toString());
    }

    private static boolean isSymmetric(int number) {
        String sNumber = Integer.toString(number);
        int len = sNumber.length() / 2;
        for (int i = 0; i < len; i++) {
            char front = sNumber.charAt(i);
            char back = sNumber.charAt(sNumber.length() - i - 1);

            if (front != back) {
                return false;
            }
        }

        return true;
    }
}
