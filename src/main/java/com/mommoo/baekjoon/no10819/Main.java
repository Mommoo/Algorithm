package com.mommoo.baekjoon.no10819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int EMPTY = -999;

    public static void main(String[] args) throws Exception {
        int[] numbers = getInputNumbers();

        int[] shuffledNumbers = new int[numbers.length];
        Arrays.fill(shuffledNumbers, EMPTY);

        System.out.println(computeMaxResult(numbers, 0, new boolean[numbers.length], shuffledNumbers));
    }

    private static int[] getInputNumbers() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        int[] numbers = new int[size];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        reader.close();

        for (int i = 0 ; i < size; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        return numbers;
    }

    private static int compute(int[] numbers) {
        int result = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            result += Math.abs(numbers[i] - numbers[i+1]);
        }
        return result;
    }

    private static int computeMaxResult(int[] originNumbers, int nextIndex, boolean[] indexUseCheckers, int[] shuffledNumbers) {
        if (shuffledNumbers[shuffledNumbers.length - 1] != EMPTY) {
            return compute(shuffledNumbers);
        }

        int maxResult = Integer.MIN_VALUE;
        for (int i = 0 ; i < indexUseCheckers.length; i++) {
            if (indexUseCheckers[i]) {
                continue;
            }

            int number = originNumbers[i];
            shuffledNumbers[nextIndex] = number;
            indexUseCheckers[i] = true;

            maxResult = Math.max(maxResult, computeMaxResult(originNumbers, nextIndex+1, indexUseCheckers, shuffledNumbers));

            shuffledNumbers[nextIndex] = EMPTY;
            indexUseCheckers[i] = false;
        }

        return maxResult;
    }
}
