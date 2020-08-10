package com.mommoo.baekjoon.no1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        Set<String> numberSets = new HashSet<>();
        while (N -- > 0) {
            numberSets.add(tokenizer.nextToken());
        }

        int M = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());
        StringBuilder builder = new StringBuilder();
        while (M -- > 0) {
            builder.append(numberSets.contains(tokenizer.nextToken()) ? 1: 0)
                   .append("\n");
        }

        System.out.println(builder);
    }
}
