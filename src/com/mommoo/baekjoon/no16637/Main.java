package com.mommoo.baekjoon.no16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main {
    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    private static int EXPRESSION_LENGTH;
    private static String EXPRESSION;

    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        setUp();
        int maxScore = Integer.MIN_VALUE;
        for (String expression : computeConditions()) {
            maxScore = Math.max(maxScore, compute(expression));
        }
        System.out.println(maxScore);
    }

    public static void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EXPRESSION_LENGTH = Integer.parseInt(reader.readLine());
        EXPRESSION = reader.readLine();

        reader.close();
    }

    public static Set<String> computeConditions() {
        List<Integer> positions = findOperationPositions(EXPRESSION);

        Set<String> expressions = new HashSet<>();
        computeConditions(expressions, positions, "", 1, false);

        return expressions;
    }

    public static void computeConditions(Set<String> expressions, List<Integer> positions, String indexJoining, int nextIndex, boolean isOpen) {
        if (nextIndex >= positions.size()) {
            if (!isOpen) {
                expressions.add(buildExpression(indexJoining));
            }
            return;
        }
        //골랐을때
        String newIndexJoining = indexJoining + (indexJoining.length() != 0 ? "," : "") + positions.get(nextIndex);
        computeConditions(expressions, positions, newIndexJoining, nextIndex + 1, !isOpen);

        if (isOpen) {
            return;
        }

        //고르지 않고 넘어갔을때
        computeConditions(expressions, positions, indexJoining, nextIndex+1, false);
    }

    public static String buildExpression(String indexJoining) {

        if (indexJoining.length() == 0) {
            return EXPRESSION;
        }

        BUILDER.setLength(0);

        String[] sPositions = indexJoining.split(",");
        int[] positions = new int[sPositions.length];
        for (int i = 0 ; i < sPositions.length; i++) {
            positions[i] = Integer.parseInt(sPositions[i]);
        }
        int openIndex = 0;
        int closeIndex = 1;

        for (int i = 0 ; i < EXPRESSION_LENGTH; i++) {
            if (openIndex < positions.length && positions[openIndex] - 1 == i) {
                BUILDER.append(OPEN);
                openIndex += 2;
            }

            if (closeIndex < positions.length && positions[closeIndex] == i) {
                BUILDER.append(CLOSE);

                closeIndex += 2;
            }

            BUILDER.append(EXPRESSION.charAt(i));
        }
        if (closeIndex == positions.length - 1) {
            BUILDER.append(CLOSE);
        }

        return BUILDER.toString();
    }

    public static List<Integer> findOperationPositions(String expression) {
        List<Integer> operationIndexes = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!isNumber(c)) {
                operationIndexes.add(i);
            }
        }

        operationIndexes.add(expression.length());
        return operationIndexes;
    }

    public static boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }

    public static int compute(String expression) {
        Queue<Integer> numbers = new LinkedList<>();
        char operation = '+';

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (isNumber(c)) {
                numbers.offer(c - '0');
                if (numbers.size() == 2) {
                    numbers.offer(calculate(numbers.poll(), numbers.poll(), operation));
                }
            }

            if (OPEN == c) {
                int closeIndex = expression.indexOf(CLOSE, i);
                numbers.offer(compute(expression.substring(i+1,closeIndex)));
                if (numbers.size() == 2) {
                    numbers.offer(calculate(numbers.poll(), numbers.poll(), operation));
                }

                i = closeIndex;
                continue;
            }

            operation = c;
        }

        return numbers.poll();
    }

    public static int calculate(int number1, int number2, char operation) {
        switch (operation) {
            case '+': return number1 + number2;
            case '-': return number1 - number2;
            case '*': return number1 * number2;
            default: return -1;
        }
    }
}
