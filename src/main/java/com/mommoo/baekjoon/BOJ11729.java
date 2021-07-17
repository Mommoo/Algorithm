package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11729 {
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        StringBuilder builder = new StringBuilder();
        int count = hanoi(1, 3, N, builder);
        System.out.println(count);
        System.out.println(builder.toString());
    }

    private static int hanoi(int from, int to, int n, StringBuilder builder) {
        if (n == 1) {
            builder.append(from)
                   .append(" ")
                   .append(to)
                   .append("\n");
            return 1;
        }

        int count = 0;

        int other = 6 - from - to;
        count += hanoi(from, other, n - 1, builder);
        count += hanoi(from, to, 1, builder);
        count += hanoi(other, to, n - 1, builder);

        return count;
    }
}
