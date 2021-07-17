package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5598 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word = reader.readLine();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            c -= 3;
            if (c < 'A') {
                c += 'Z' - 'A' + 1;
            }
            builder.append(c);
        }
        System.out.println(builder);
        reader.close();
    }
}
