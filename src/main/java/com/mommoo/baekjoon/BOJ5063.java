package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5063 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());
        while (testCase -- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int earnWithNoAd = Integer.parseInt(tokenizer.nextToken());
            int earnWithAd = Integer.parseInt(tokenizer.nextToken());
            int adPrice = Integer.parseInt(tokenizer.nextToken());

            int earnSub = earnWithNoAd - (earnWithAd - adPrice);
            if (earnSub > 0) {
                System.out.println("do not advertise");
            } else if (earnSub == 0) {
                System.out.println("does not matter");
            } else {
                System.out.println("advertise");
            }
        }
    }
}
