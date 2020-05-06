package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2914 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int songCount = Integer.parseInt(tokenizer.nextToken());
        int average = Integer.parseInt(tokenizer.nextToken());
        System.out.println((songCount * (average - 1)) + 1);
    }
}
