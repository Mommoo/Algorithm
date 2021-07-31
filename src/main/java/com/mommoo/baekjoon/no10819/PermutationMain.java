package com.mommoo.baekjoon.no10819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PermutationMain {
    public static void main(String[] args) throws Exception {
        int[] numbers = getInputNumbers();
        System.out.println(computeMaxResult(numbers, 0));
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

    private static int computeMaxResult(int[] numbers, int depth) {
        if (depth == numbers.length - 1) {
            return compute(numbers);
        }

        int maxResult = Integer.MIN_VALUE;

        for (int i = depth; i < numbers.length; i++) {
            int target = numbers[depth];
            numbers[depth] = numbers[i];
            numbers[i] = target;

            maxResult = Math.max(maxResult, computeMaxResult(numbers, depth+1));

            numbers[i] = numbers[depth];
            numbers[depth] = target;
        }

        return maxResult;
    }
}
