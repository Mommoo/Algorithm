package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2810 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        String seats = reader.readLine();

        boolean leftAvailable = true;

        int useCount = 0;

        for (int i = 0 ; i < N; i++) {
            char seat = seats.charAt(i);
            if (seat == 'S') {
                useCount++;
            } else {
                if (leftAvailable) {
                    useCount++;
                }
                i++;
                useCount++;
                leftAvailable = false;
            }
        }

        System.out.println(useCount);
    }
}
