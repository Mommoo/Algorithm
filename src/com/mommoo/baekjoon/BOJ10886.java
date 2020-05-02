package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ10886 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int cuteCount = 0;
        for (int i = 0 ; i < N ; i++) {
            cuteCount += reader.readLine().charAt(0) - '0';
        }

        System.out.printf("Junhee is%s cute!", cuteCount * 2 > N ? "" : " not");
    }
}
