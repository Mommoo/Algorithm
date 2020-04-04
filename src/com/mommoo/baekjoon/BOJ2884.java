package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2884 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer time = new StringTokenizer(reader.readLine());
        int hour = Integer.parseInt(time.nextToken());
        int min = Integer.parseInt(time.nextToken());

        if (min < 45) {
            if (hour == 0) {
                hour = 23;
            } else {
                hour -= 1;
            }

            min += 15;
        } else {
            min -= 45;
        }

        System.out.println(hour + " " + min);
    }
}
