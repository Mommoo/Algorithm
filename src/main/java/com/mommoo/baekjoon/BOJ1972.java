package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ1972 {
    private static final String IS_SURPRISING = " is surprising.";
    private static final String IS_NOT_SURPRISING = " is NOT surprising.";

    public static void main(String[] args) throws Exception {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String word = reader.readLine();
                if ("*".equals(word)) {
                    break;
                }

                builder.append(word)
                       .append(isSurprising(word) ? IS_SURPRISING : IS_NOT_SURPRISING)
                       .append(System.lineSeparator());
            }
        }

        System.out.println(builder);
    }

    private static boolean isSurprising(String word) {
        int len = word.length();
        int N = len - 2;

        for (int spaceLen = 1 ; spaceLen <= N; spaceLen ++) {
            if (isExistDuplicateGroups(word, spaceLen)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isExistDuplicateGroups(String word, int spaceLen) {
        int len = word.length();
        Set<String> groups = new HashSet<>();
        int nextIndex = 0;
        while (nextIndex + spaceLen < len) {
            char first = word.charAt(nextIndex);
            char second = word.charAt(nextIndex + spaceLen);
            String group = first + String.valueOf(second);
            if (groups.contains(group)) {
                return true;
            }

            groups.add(group);
            nextIndex++;
        }

        return false;
    }
}
