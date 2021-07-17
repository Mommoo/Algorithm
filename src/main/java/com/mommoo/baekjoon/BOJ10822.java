package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10822 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numbers = br.readLine();
        br.close();
        StringBuilder builder = new StringBuilder();

        int sum = 0;
        for (int i = 0; i < numbers.length(); i++) {
            char c = numbers.charAt(i);
            if (c == ',') {
                sum += Integer.parseInt(builder.toString());
                builder.setLength(0);
            } else {
                builder.append(c);
            }
        }

        sum += Integer.parseInt(builder.toString());
        System.out.println(sum);
    }
}
