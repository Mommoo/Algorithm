package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2193 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        long[] dp = new long[91];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i < N + 1 ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[N]);
//        char[] elements = new char[N];
//        System.out.println(countSpecialBinaryNumber(elements, 0, 0));
    }

//    private static int countSpecialBinaryNumber(char[] elements, int nextIndex, int size) {
//        if (elements[0] == '0') {
//            return 0;
//        } else if (nextIndex > 1 && elements[nextIndex - 2] == '1' && elements[nextIndex - 1] == '1') {
//            return 0;
//        } else if (size == elements.length) {
//            return 1;
//        }
//
//        int totalCount = 0;
//        for (;nextIndex < elements.length; nextIndex++) {
//            elements[nextIndex] = '0';
//            totalCount += countSpecialBinaryNumber(elements, nextIndex + 1, size + 1);
//
//            elements[nextIndex] = '1';
//            totalCount += countSpecialBinaryNumber(elements, nextIndex + 1, size + 1);
//        }
//
//        return totalCount;
//    }
}
