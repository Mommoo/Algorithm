package com.mommoo.baekjoon.no14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int PLUS = 0;
    private static final int MINUS = 1;
    private static final int MULTIPLY = 2;
    private static final int DIVIDE = 3;
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberCount = Integer.parseInt(reader.readLine());
        int[] nums = createNums(numberCount, reader);
        int[] operations = createOperations(reader);

        dfs(operations, nums, 1, nums[0], computeRemainOperationCount(operations));
        System.out.println(max);
        System.out.println(min);
    }

    private static int[] createNums(int size, BufferedReader reader) throws IOException {
        int[] nums = new int[size];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i <  size; i++) {
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return nums;
    }

    private static int[] createOperations(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] operations = new int[4];
        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return operations;
    }

    private static int computeRemainOperationCount(int[] operations) {
        int sum = 0;
        for (int operationCount: operations) {
            sum += operationCount;
        }
        return sum;
    }

    private static void dfs(int[] remainOperations, int[] nums, int index, int result, int remainCount) {
        if (remainCount == 0) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int i = 0; i < 4 ; i++) {
            int[] newOperations = Arrays.copyOf(remainOperations, 4);
            if (newOperations[i] != 0) {
                newOperations[i]--;
                int newResult = compute(i, result, nums[index]);
                dfs(newOperations, nums, index + 1, newResult, remainCount - 1);
            }
        }

    }

    private static int compute(int operationType, int num1, int num2) {
        switch(operationType) {
            case PLUS: return num1 + num2;
            case MINUS: return num1 - num2;
            case MULTIPLY: return num1 * num2;
            case DIVIDE: return num1 / num2;
        }

        return -1;
    }
}
