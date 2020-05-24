package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ7567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String dishString = br.readLine();
        int height = 10;
        for (int i = 1 ; i < dishString.length(); i++) {
            char previous = dishString.charAt(i - 1);
            char current = dishString.charAt(i);
            if (previous == current) {
                height += 5;
            } else {
                height += 10;
            }
        }

        bw.write(String.valueOf(height));

        bw.close();
        br.close();
    }
}
