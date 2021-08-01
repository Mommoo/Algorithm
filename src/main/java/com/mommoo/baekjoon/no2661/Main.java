package com.mommoo.baekjoon.no2661;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        int len = getInputLen();

        StringBuilder builder = new StringBuilder(90);

        System.out.println(computeByDFS(builder, len));
    }

    private static int getInputLen() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(reader.readLine());

        reader.close();

        return len;
    }

    private static boolean isInvalid(StringBuilder builder) {
        int chunkLen = builder.length() / 2;

        for (int chunk = 1; chunk <= chunkLen; chunk++) {
            for (int i = chunk; i <= builder.length() - chunk; i++) {
                if (isPreviousAllMatched(builder, i, chunk)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isPreviousAllMatched(StringBuilder builder, int beginIndex, int chunk) {
        int prevIndex = beginIndex - chunk;
        for (int i = 0; i < chunk; i++) {
            if (builder.charAt(beginIndex + i) != builder.charAt(prevIndex + i)) {
                return false;
            }
        }
        return true;
    }

    private static String computeByDFS(StringBuilder builder, int len) {
        if (isInvalid(builder)) {
            return null;
        }

        if (builder.length() == len) {
            return builder.toString();
        }

        for (int number = 1; number <= 3; number++) {
            int preLen = builder.length();
            builder.append(number);

            String result = computeByDFS(builder, len);

            if (result != null) {
                return result;
            }

            builder.setLength(preLen);
        }

        return null;
    }
}
