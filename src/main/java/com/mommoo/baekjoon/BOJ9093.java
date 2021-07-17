package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ9093 {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Stack<Character> elements = new Stack<>();

        while (N -- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                for (int i = 0 ; i < word.length(); i++) {
                    elements.push(word.charAt(i));
                }

                while (!elements.isEmpty()) {
                    BUILDER.append(elements.pop());
                }
                BUILDER.append(" ");
            }

            BUILDER.append("\n");
        }

        System.out.println(BUILDER);
    }
}
