package com.mommoo.baekjoon.no2475;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        br.close();

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            sum += Math.pow(num, 2);
        }

        System.out.println(sum % 10);
    }
}
