package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BOJ9933 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, List<String>> wordsFinder = new HashMap<>();
        int N = Integer.parseInt(reader.readLine());
        while (N-- > 0) {
            String word = reader.readLine();
            wordsFinder.computeIfAbsent(word.length(), len -> new LinkedList<>());
            wordsFinder.get(word.length()).add(word);
        }

        for (List<String> words: wordsFinder.values()) {
            String findPassword = findPasswordIn(words);
            if (Objects.nonNull(findPassword)) {
                System.out.println(resolveOutput(findPassword));
                break;
            }
        }
    }

    private static String findPasswordIn(List<String> words) {
        return words.stream()
                .map(basic -> findPasswordIn(basic, words))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    private static String findPasswordIn(String basic, List<String> words) {
        return words.stream()
                    .filter(target -> isPassword(basic, target))
                    .findFirst()
                    .orElse(null);
    }

    private static boolean isPassword(String basic, String target) {
        for (int i = 0; i < basic.length(); i++) {
            if (basic.charAt(i) != target.charAt(basic.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }

    private static String resolveOutput(String word) {
        int len = word.length();
        char middle = word.charAt(len/2);
        return len + " " + middle;
    }
}
