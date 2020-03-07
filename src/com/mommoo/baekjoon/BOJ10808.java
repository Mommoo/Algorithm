package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BOJ10808 {
    public static void main(String[] args) throws Exception {
        int[] alphabet = new int[26];
        String word = new BufferedReader(new InputStreamReader(System.in)).readLine();
        for (int idx = 0 ; idx < word.length(); idx++) {
            int position = word.charAt(idx) - 'a';
            alphabet[position]++;
        }

        System.out.println(Arrays.stream(alphabet)
                                 .boxed()
                                 .map(String::valueOf)
                                 .collect(Collectors.joining(" ")));
    }
}
