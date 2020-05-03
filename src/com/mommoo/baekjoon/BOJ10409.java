package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10409 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int taskCount = Integer.parseInt(tokenizer.nextToken());
        int limitTotalTaskAmount = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        int takenTotalTask = 0;
        int takenTaskCount = 0;
        while (tokenizer.hasMoreTokens()) {
            int task = Integer.parseInt(tokenizer.nextToken());
            if (takenTotalTask + task > limitTotalTaskAmount) {
                break;
            }
            takenTotalTask += task;
            takenTaskCount++;
        }

        System.out.println(takenTaskCount);
    }
}
