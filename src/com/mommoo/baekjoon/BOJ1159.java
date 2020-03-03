package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BOJ1159 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Map<Character, Integer> counter = new HashMap<>();
        while (N-- > 0) {
            char c = reader.readLine().charAt(0);
            int count = counter.getOrDefault(c, 0);
            counter.put(c, count + 1);
        }

        String elements = peekElementsMoreFiveCount(counter);
        elements = elements.isEmpty() ? "PREDAJA" : elements;
        System.out.println(elements );
        reader.close();
    }

    private static String peekElementsMoreFiveCount(Map<Character, Integer> counter) {
        return counter.keySet()
                      .stream()
                      .filter(element -> counter.get(element) >= 5)
                      .map(String::valueOf)
                      .collect(Collectors.joining());
    }
}
