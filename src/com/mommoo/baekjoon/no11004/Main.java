package com.mommoo.baekjoon.no11004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        Long[] nums = new Long[N];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0 ; i < N; i++) {
            nums[i] = Long.parseLong(tokenizer.nextToken());
        }

        Arrays.sort(nums);
        System.out.println(nums[K - 1]);
    }
}
