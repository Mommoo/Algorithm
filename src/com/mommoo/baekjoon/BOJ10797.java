package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10797 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sDate = reader.readLine();
        StringTokenizer carNumbers = new StringTokenizer(reader.readLine());

        int count = 0;
        while (carNumbers.hasMoreTokens()) {
            String carNumber = carNumbers.nextToken();
            count += carNumber.equals(sDate) ? 1 : 0;
        }

        System.out.println(count);
    }
}
