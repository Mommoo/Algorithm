package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1120 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        String A = tokenizer.nextToken();
        String B = tokenizer.nextToken();
        System.out.println(computeMinimumDiffer(A, B));
        reader.close();
    }

    private static int computeMinimumDiffer(String A, String B) {
        int subLen = B.length() - A.length();

        if (subLen == 0) {
            return compareDifferCount(A, B);
        }

        int minValue = Integer.MAX_VALUE;

        for (int i = 0 ; i <= subLen; i++) {
            String prefix = B.substring(0, i);
            String postFix = B.substring(B.length() - (subLen - i));
            minValue = Math.min(computeMinimumDiffer(prefix + A + postFix, B), minValue);
        }

        return minValue;
    }

    private static int compareDifferCount(String A, String B) {
        int count = 0;
        for (int i = 0; i < A.length() ; i++) {
            if (A.charAt(i) != B.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
