package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ10987 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        System.out.println(reader.readLine()
                                 .chars()
                                 .mapToObj(intChar -> (char)intChar)
                                 .filter(vowels::contains)
                                 .count());
        reader.close();
    }
}
