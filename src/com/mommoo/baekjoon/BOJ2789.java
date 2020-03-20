package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

public class BOJ2789 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Character> groups = "CAMBRIDGE".chars()
                                           .mapToObj(charInt -> (char) charInt)
                                           .collect(Collectors.toSet());

        String result = reader.readLine().chars()
                              .mapToObj(charInt -> (char) charInt)
                              .filter(c -> !groups.contains(c))
                              .map(String::valueOf)
                              .collect(Collectors.joining());

        reader.close();
        System.out.println(result);
    }
}
