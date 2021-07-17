package com.mommoo.baekjoon.no4673;

public class Main {
    public static void main(String[] args) {
        boolean[] selfNumbers = new boolean[10_001];
        for (int number = 1 ; number < 10_001; number++) {
            int numberCreatedByConstructor = constructor(number);
            if (numberCreatedByConstructor > 10_000) {
                continue;
            }
            selfNumbers[numberCreatedByConstructor] = true;
        }

        StringBuilder builder = new StringBuilder();
        for (int number = 1 ; number < 10_001; number++) {
            if (selfNumbers[number]) {
                continue;
            }

            builder.append(number)
                   .append("\n");
        }

        System.out.println(builder);
    }

    public static int constructor(int number) {
        int origin = number;
        while (origin != 0) {
            number += origin % 10;
            origin /= 10;
        }

        return number;
    }
}
