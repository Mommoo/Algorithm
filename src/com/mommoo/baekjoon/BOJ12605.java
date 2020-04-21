package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ12605 {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Stack<String> elements = new Stack<>();

        for (int order = 1; order <= N; order++) {
            appendPrefix(order);

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            while (tokenizer.hasMoreTokens()) {
                elements.push(tokenizer.nextToken());
            }

            while (!elements.isEmpty()) {
                appendElement(elements.pop());
            }

            appendSuffix();
        }

        System.out.println(BUILDER);
    }

    private static void appendPrefix(int order) {
        BUILDER.append("Case #")
               .append(order)
               .append(":");
    }

    private static void appendElement(String element) {
        BUILDER.append(" ")
               .append(element);
    }

    private static void appendSuffix() {
        BUILDER.append("\n");
    }
}
