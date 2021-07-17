package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2804 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        reader.close();

        String A = tokenizer.nextToken(), B = tokenizer.nextToken();

        StringBuilder builder = new StringBuilder();
        int[] crossIndexes = findCrossIndexes(A, B);

        for (int raw = 0 ; raw < B.length(); raw++) {
            if (raw == crossIndexes[1]) {
                builder.append(A).append(NEW_LINE);
                continue;
            }

            for (int col = 0 ; col < A.length(); col++) {
                char c = col == crossIndexes[0] ? B.charAt(raw) : '.';
                builder.append(c);
            }

            builder.append(NEW_LINE);
        }

        System.out.println(builder);
    }

    private static int[] findCrossIndexes(String A, String B) {
        for (int i = 0; i < A.length(); i++) {
            char aChar = A.charAt(i);
            for (int j = 0; j < B.length(); j++) {
                char bChar = B.charAt(j);
                if (aChar == bChar) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
