package com.mommoo.baekjoon.no15658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MUL = '*';
    private static final char DIV = '/';
    private static final List<String> OPERANDS = new LinkedList<>();

    private static int[] OPERATIONS;
    private static int PLUS_COUNT, MINUS_COUNT, MUL_COUNT, DIV_COUNT, OPERAND_COUNT;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int operationCount = Integer.parseInt(reader.readLine());

        OPERATIONS = createOperations(operationCount, reader);

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        PLUS_COUNT = Integer.parseInt(tokenizer.nextToken());
        MINUS_COUNT = Integer.parseInt(tokenizer.nextToken());
        MUL_COUNT = Integer.parseInt(tokenizer.nextToken());
        DIV_COUNT = Integer.parseInt(tokenizer.nextToken());
        OPERAND_COUNT = operationCount - 1;

        reader.close();

        fillOperatorCombinations(PLUS_COUNT, MINUS_COUNT, MUL_COUNT, DIV_COUNT, "");

        int maxResult = Integer.MIN_VALUE;
        int minResult = Integer.MAX_VALUE;

        for (String operand: OPERANDS) {
            int result = compute(operand);
            maxResult = Math.max(maxResult, result);
            minResult = Math.min(minResult, result);
        }

        System.out.println(maxResult);
        System.out.println(minResult);
    }

    private static int[] createOperations(int operationCount, BufferedReader reader) throws IOException {
        int[] operations = new int[operationCount];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0 ; i < operationCount; i++) {
            operations[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return operations;
    }

    private static void fillOperatorCombinations(int plusCount, int minusCount, int mulCount, int divCount, String operand) {
        if (operand.length() == OPERAND_COUNT) {
            OPERANDS.add(operand);
            return;
        }

        if (plusCount > 0) {
            fillOperatorCombinations(plusCount - 1, minusCount, mulCount, divCount, operand + PLUS);
        }

        if (minusCount > 0) {
            fillOperatorCombinations(plusCount, minusCount - 1, mulCount, divCount, operand + MINUS);
        }

        if (mulCount > 0) {
            fillOperatorCombinations(plusCount, minusCount, mulCount - 1, divCount, operand + MUL);
        }

        if (divCount > 0) {
            fillOperatorCombinations(plusCount, minusCount, mulCount, divCount - 1, operand + DIV);
        }
    }

    private static int compute(String operand) {
        int result = OPERATIONS[0];
        for (int i = 1 ; i < OPERATIONS.length; i++) {
            char operator = operand.charAt(i - 1);
            int num = OPERATIONS[i];
            result = computeNumbers(operator, result, num);
        }
        return result;
    }

    private static int computeNumbers(char operator, int num1, int num2) {
        switch (operator) {
            case PLUS: return num1 + num2;
            case MINUS: return num1 - num2;
            case MUL: return num1 * num2;
            case DIV: return num1 / num2;
        }

        return -1;
    }
}
