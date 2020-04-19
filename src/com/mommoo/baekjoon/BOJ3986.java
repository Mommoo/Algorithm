package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ3986 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int answer = 0;
        while (N-- > 0) {
            answer += isGoodWord(reader.readLine()) ? 1 : 0;
        }

        System.out.println(answer);
    }

    private static boolean isGoodWord(String word) {
        Stack<Character> chars = new Stack<>();
        chars.push(word.charAt(0));

        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!chars.isEmpty() && chars.peek() == c) {
                chars.pop();
            } else {
                chars.push(c);
            }
        }

        return chars.isEmpty();
    }
}
