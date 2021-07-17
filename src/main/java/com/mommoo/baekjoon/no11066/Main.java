package com.mommoo.baekjoon.no11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final long[][] DP = new long[501][501];
    private static final long[] FILE_SIZES = new long[501];
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        while (TC -- > 0) {
            int size = Integer.parseInt(br.readLine());
            initialize(br);
            long minimumFileSumCost = computeMinimumFileSumCost(0, size - 1);
            appendResult(minimumFileSumCost);
        }

        System.out.println(BUILDER);
    }

    private static void initialize(BufferedReader br) throws IOException {
        for (long[] dpLine: DP) {
            Arrays.fill(dpLine, 0L);
        }
        Arrays.fill(FILE_SIZES, 0L);

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int index = 0;
        while (tokenizer.hasMoreTokens()) {
            FILE_SIZES[index++] = Long.parseLong(tokenizer.nextToken());
        }
    }

    private static long computeMinimumFileSumCost(int beginIndex, int endIndex) {
        if (DP[beginIndex][endIndex] != 0L) {
            return DP[beginIndex][endIndex];
        }

        if (beginIndex == endIndex) {
            DP[beginIndex][endIndex] = FILE_SIZES[beginIndex];
        } else if (beginIndex + 1 == endIndex) {
            DP[beginIndex][endIndex] = FILE_SIZES[beginIndex] + FILE_SIZES[endIndex];
        } else {
            long sumCost = 0;
            for (int i = beginIndex; i <= endIndex; i++) {
                sumCost += FILE_SIZES[i];
            }

            long minAdditionalCost = Long.MAX_VALUE;
            for (int i = beginIndex; i < endIndex; i++) {
                long leftCost = computeMinimumFileSumCost(beginIndex, i);
                long rightCost = computeMinimumFileSumCost(i + 1, endIndex);
                long additionalCost = 0L;
                if (beginIndex != i) {
                    additionalCost += leftCost;
                }

                if (endIndex != (i + 1)) {
                    additionalCost += rightCost;
                }

                minAdditionalCost = Math.min(minAdditionalCost, additionalCost);
            }
            DP[beginIndex][endIndex] = sumCost + minAdditionalCost;
        }

        return DP[beginIndex][endIndex];
    }

    private static void appendResult(long result) {
        BUILDER.append(result)
               .append("\n");
    }
}
