package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class BOJ1919 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word1 = reader.readLine();
        String word2 = reader.readLine();

        Map<Character, Integer> wordGroup1 = new HashMap<>();
        for (int i = 0; i < word1.length(); i++) {
            char c1 = word1.charAt(i);
            Integer count = wordGroup1.getOrDefault(c1, 0);
            wordGroup1.put(c1, count + 1);
        }

        Map<Character, Integer> wordGroup2 = new HashMap<>();
        for (int i = 0; i < word2.length(); i++) {
            char c2 = word2.charAt(i);
            if (wordGroup1.containsKey(c2)) {
                Integer count = wordGroup1.get(c2);
                wordGroup1.put(c2, count - 1);

                if (count == 1) {
                    wordGroup1.remove(c2);
                }

                continue;
            }

            Integer count = wordGroup2.getOrDefault(c2, 0);
            wordGroup2.put(c2, count + 1);
        }

        int totalGroupCount = Stream.of(wordGroup1, wordGroup2)
                                    .map(Map::values)
                                    .flatMap(Collection::stream)
                                    .mapToInt(Integer::intValue)
                                    .sum();

        System.out.println(totalGroupCount);
    }
}
