package com.mommoo.programmers.level1;

import java.util.Arrays;

public class 예산 {
    public static void main(String[] args) {
        System.out.println(new 예산().solution(new int[]{1, 3, 2, 5, 4}, 9));
        System.out.println(new 예산().solution(new int[]{2, 2, 3, 3}, 10));
    }

    public int solution(int[] d, int budget) {
        Arrays.sort(d);
        int num = 0;
        for (int value : d) {
            if (value > budget) {
                break;
            }

            budget -= value;
            num++;
        }
        return num;
    }
}
