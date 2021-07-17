package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2902 {
    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine(), "-");
        StringBuilder builder = new StringBuilder();
        while (tokenizer.hasMoreElements()) {
            builder.append(tokenizer.nextToken().charAt(0));
        }
        System.out.println(builder);
    }
}
