package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1773 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int countStudent = Integer.parseInt(tokenizer.nextToken());
        int totalFireTime = Integer.parseInt(tokenizer.nextToken());
        boolean[] fireTimes = new boolean[2000001];
        while (countStudent -- > 0) {
            int studentFireTime = Integer.parseInt(reader.readLine());
            int timeCycle = studentFireTime;
            while (studentFireTime <= totalFireTime) {
                fireTimes[studentFireTime] = true;
                studentFireTime += timeCycle;
            }
        }

        int count = 0;
        for (boolean isFire: fireTimes) {
            count += isFire ? 1: 0;
        }
        System.out.println(count);
    }
}
