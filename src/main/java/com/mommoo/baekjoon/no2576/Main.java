package com.mommoo.baekjoon.no2576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] NUMS;

    public static void main(String[] args) throws IOException {
        NUMS = createNums();

        if (hasNotAnyEvenNumber()) {
            System.out.println(-1);
            return;
        }

        printEvenSumAndMinEven();
    }

    private static int[] createNums() throws IOException {
        int[] nums = new int[7];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0 ; i < 7; i++) {
            nums[i] = Integer.parseInt(reader.readLine());
        }
        reader.close();
        return nums;
    }

    private static boolean hasNotAnyEvenNumber() {
        for (int num : NUMS) {
            if (num % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    private static void printEvenSumAndMinEven() {
        int evenSum = 0;
        int minEvenNum = Integer.MAX_VALUE;

        for (int num : NUMS) {
            if (num % 2 != 0) {
                if (minEvenNum > num) {
                    minEvenNum = num;
                }
                evenSum += num;
            }
        }

        System.out.println(evenSum);
        System.out.println(minEvenNum);
    }
}
