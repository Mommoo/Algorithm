package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ1316 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;

        int N = Integer.parseInt(reader.readLine());
        while (N -- > 0) {
            count += isGroupWord(reader.readLine()) ? 1 : 0;
        }

        System.out.println(count);
        reader.close();
    }

    public static boolean isGroupWord(String word) {
        Set<Character> expires = new HashSet<>();
        char previous = word.charAt(0);
        for (int idx = 1; idx < word.length(); idx++) {
            char current = word.charAt(idx);

            if (previous == current) {
                continue;
            }

            if (expires.contains(current) ) {
                return false;
            }

            expires.add(previous);
            previous = current;
        }

        return true;
    }
}
