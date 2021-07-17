package com.mommoo.practice;

import java.util.Arrays;

public class FailureFunction {
    private final String string;
    private final int[] failureFunction;
    private final int[] answer;

    private FailureFunction(String string, int[] answer) {
        this.string = string;
        this.failureFunction = buildFailureFunction(string);
        this.answer = answer;
    }

    public static void main(String[] args) {
        System.out.println(new FailureFunction("abaabac", new int[]{0, 0, 1, 1, 2, 3, 0}));
        System.out.println(new FailureFunction("aabaa", new int[]{0, 1, 0, 1, 2}));
        System.out.println(new FailureFunction("adbbadad", new int[]{0, 0, 0, 0, 1, 2, 1, 2}));
        System.out.println(new FailureFunction("ababac", new int[]{0, 0, 1, 2, 3, 0}));
    }

    private static int[] buildFailureFunction(String string) {
        int len = string.length();
        int[] failureFunction = new int[len];
        for (int i = 1; i < len; i++) {
            int previousIndex = i - 1;
            while (true) {
                int beginIndex = failureFunction[previousIndex] - 1;
                if (string.charAt(beginIndex + 1) == string.charAt(i)) {
                    failureFunction[i] = failureFunction[previousIndex] + 1;
                    break;
                }

                if (beginIndex == -1) {
                    break;
                }

                previousIndex = beginIndex;
            }
        }
        return failureFunction;
    }

    @Override
    public String toString() {
        return "FailureFunction{" +
               "string='" + string + '\'' +
               ", failureFunction=" + Arrays.toString(failureFunction) +
               ", isRight=" + Arrays.equals(failureFunction, answer) +
               '}';
    }
}
