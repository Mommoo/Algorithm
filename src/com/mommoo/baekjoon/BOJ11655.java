package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine(); br.close();
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < word.length(); i++) {
            char c = word.charAt(i);

            if (isNotAlphabet(c)) {
                builder.append(c);
                continue;
            }

            builder.append(pullAlphabet(c));
        }

        System.out.println(builder);
    }

    private static boolean isNotAlphabet(char c) {
        return !('a' <= c && c <= 'z') && !('A' <= c && c <= 'Z');
    }

    private static char pullAlphabet(char c) {
        char pullAlphabet = (char)(c + 13);
        boolean isLowerCase = 'a' <= c && c <= 'z';
        if ((isLowerCase && pullAlphabet > 'z') || (!isLowerCase && pullAlphabet > 'Z')) {
            pullAlphabet -= 26;
        }

        return pullAlphabet;
    }
}
