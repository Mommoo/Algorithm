package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10817 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        br.close();

        int first = Integer.parseInt(tokenizer.nextToken());
        int second = Integer.parseInt(tokenizer.nextToken());
        int third = Integer.parseInt(tokenizer.nextToken());

        if ((first <= second && first >= third) || (first >= second && first <= third)) {
            System.out.println(first);
        } else if ((second <= first && second >= third) || (second >= first && second <= third)) {
            System.out.println(second);
        } else {
            System.out.println(third);
        }
    }
}
