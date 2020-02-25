package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BOJ10809 {
    public static void main(String[] args) throws Exception {
        Map<Character, Integer> counter = createCounter();
        String string = getString();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (counter.get(c) == -1) {
                counter.put(c, i);
            }
        }

        System.out.println(counter.values()
                                  .stream()
                                  .map(String::valueOf)
                                  .collect(Collectors.joining(" ")));

    }

    private static Map<Character, Integer> createCounter() {
        Map<Character, Integer> counter = new HashMap<>();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            counter.put(alphabet, -1);
        }
        return counter;
    }

    private static String getString() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        reader.close();

        return string;
    }
}
