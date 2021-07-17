package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ10988 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word = reader.readLine();
        System.out.println(isPalindrome(word) ? 1 : 0);
        reader.close();
    }

    private static boolean isPalindrome(String word) {
        int len = word.length();
        for (int i = 0; i < len; i++) {
            char begin = word.charAt(i);
            char last = word.charAt(len - i - 1);

            if (begin != last) {
                return false;
            }
        }

        return true;
    }
}
