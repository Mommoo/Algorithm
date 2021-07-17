package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class BOJ1110 {
    public static void main(String[] args) throws Exception {
        String origin = buildTwoDigitNumber(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int count = 0;

        String nextNumber = null;
        while (!Objects.equals(origin, nextNumber)) {
            count++;
            if (nextNumber == null) {
                nextNumber = origin;
            }

            int first = nextNumber.charAt(0) - '0';
            int second = nextNumber.charAt(1) - '0';

            nextNumber = nextNumber.substring(1) + buildTwoDigitNumber(first + second).substring(1);
        }

        System.out.println(count);
    }

    public static String buildTwoDigitNumber(String number) {
        return number.length() == 1 ? "0" + number : number;
    }

    public static String buildTwoDigitNumber(int number) {
        return String.format("%02d", number);
    }
}
