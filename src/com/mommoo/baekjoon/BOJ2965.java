package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2965 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().split(" ");
        int[] location = {
                Integer.parseInt(elements[0]),
                Integer.parseInt(elements[1]),
                Integer.parseInt(elements[2])
        };

        int count = 0;
        while (!(location[1] - location[0] == 1 && location[2] - location[1] == 1)) {
            if (location[1] - location[0] < location[2] - location[1]) {
                location[0] = location[1] + 1;
                swap(location, 0, 1);
            } else {
                location[2] = location[1] - 1;
                swap(location, 2, 1);
            }
            count++;
        }

        System.out.println(count);
    }

    private static void swap(int[] location, int from, int to) {
        int temp = location[from];
        location[from] = location[to];
        location[to] = temp;
    }
}
