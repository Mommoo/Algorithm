package com.mommoo.baekjoon.no2506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        String[] strings = reader.readLine().split(" ");
        int sum = strings[0].equals("1") ? 1:0;
        int prev = 1;

        for (int i = 1; i < strings.length; i++) {
            if (strings[i].equals("1")) {
                if (strings[i-1].equals("1")) {
                    prev++;
                }

                sum += prev;
            } else {
                prev = 1;
            }

        }

        System.out.println(sum);
    }
}
