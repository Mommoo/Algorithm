package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5032 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int hasBottle = Integer.parseInt(tokenizer.nextToken());
        int todayFindBottle = Integer.parseInt(tokenizer.nextToken());
        int numberOfChangeNewBottle = Integer.parseInt(tokenizer.nextToken());
        int totalHasBottle = hasBottle + todayFindBottle;

        int totalNewBottle = 0;
        while (totalHasBottle >= numberOfChangeNewBottle) {
            int countChangeNewBottle = totalHasBottle / numberOfChangeNewBottle;
            totalHasBottle -= countChangeNewBottle * numberOfChangeNewBottle;
            totalNewBottle += countChangeNewBottle;
            totalHasBottle += countChangeNewBottle;
        }

        System.out.println(totalNewBottle);
    }
}
