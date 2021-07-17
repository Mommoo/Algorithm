package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ1264 {
    private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sentence;
        while (!(sentence = reader.readLine()).equals("#")) {
            int count = (int) sentence.chars()
                                      .mapToObj(cInt -> (char)cInt)
                                      .map(Character::toLowerCase)
                                      .filter(VOWELS::contains)
                                      .count();

            BUILDER.append(count).append("\n");
        }
        System.out.println(BUILDER);
    }
}
