package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2954 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sentence = br.readLine();
        br.close();

        StringBuilder builder = new StringBuilder();
        char lastC = '-';
        for (int i = 0 ; i < sentence.length() ; i ++) {
            char c = sentence.charAt(i);

            if (c == 'p' && i > 0 && i < sentence.length() - 1) {
                if (isVowels(lastC) && sentence.charAt(i + 1) == lastC) {
                    i++;
                    lastC = '-';
                    continue;
                }
            }

            builder.append(c);
            lastC = c;
        }

        System.out.println(builder);
    }

    private static boolean isVowels(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
