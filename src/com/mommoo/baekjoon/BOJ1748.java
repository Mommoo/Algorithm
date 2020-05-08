package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1748 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        int nextExp = 1;
        int answer = 0;
        int next = (int)Math.pow(10, nextExp);
        while (number >= next) {
            int count = next - (int)Math.pow(10, nextExp - 1);
            answer += count * nextExp;
            nextExp++;
            next = (int) Math.pow(10, nextExp);
        }
        int remainNumber = number - (int) Math.pow(10, nextExp - 1) + 1;
        answer += remainNumber * nextExp;
        System.out.println(answer);
    }
}
