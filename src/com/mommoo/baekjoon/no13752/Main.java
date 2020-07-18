package com.mommoo.baekjoon.no13752;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        while (TC -- > 0) {
            int count = Integer.parseInt(br.readLine());
            for (int i = 0; i < count; i++) {
                builder.append("=");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }
}
