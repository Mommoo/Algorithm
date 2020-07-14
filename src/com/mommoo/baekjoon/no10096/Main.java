package com.mommoo.baekjoon.no10096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();
    private static final String NOT_POSSIBLE = "NOT POSSIBLE";
    private static final String NOT_UNIQUE = "NOT UNIQUE";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        if (length % 2 == 0) {
            System.out.println(NOT_POSSIBLE);
            return;
        }

        String sentence = br.readLine();
        String answer = null;
        for (int i = 0; i < length; i++) {
            String uString = computeUString(i, sentence);
            if (uString == null) {
                continue;
            }

            if (answer != null) {
                System.out.println(NOT_UNIQUE);
                return;
            }

            answer = uString;
        }

        if (answer == null) {
            System.out.println(NOT_POSSIBLE);
            return;
        }

        System.out.println(answer);
    }

    private static String computeUString(int excludeIndex, String sentence) {
        BUILDER.setLength(0);
        int uLen = (sentence.length() - 1) / 2;
        boolean isExcludeIndexOnLeft = excludeIndex < uLen;
        int leftBeginIndex = 0;
        int rightBeginIndex = uLen + (isExcludeIndexOnLeft ? 1 : 0);
        while (uLen -- > 0) {
            if (leftBeginIndex == excludeIndex) {
                leftBeginIndex++;
            }

            if (rightBeginIndex == excludeIndex) {
                rightBeginIndex++;
            }

            if (sentence.charAt(leftBeginIndex) != sentence.charAt(rightBeginIndex)) {
                return null;
            }

            BUILDER.append(sentence.charAt(leftBeginIndex));
            leftBeginIndex++;
            rightBeginIndex++;
        }

        return BUILDER.toString();
    }
}
