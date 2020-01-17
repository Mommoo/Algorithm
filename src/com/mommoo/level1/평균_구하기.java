package com.mommoo.level1;

import java.util.Arrays;

public class 평균_구하기 {
    public static void main(String[] args) {
        System.out.println(new 평균_구하기().solution(new int[]{1, 2, 3, 4}));
        System.out.println(new 평균_구하기().solution(new int[]{5, 5}));
    }

    public double solution(int[] arr) {
        double size = arr.length;
        return ((double) Arrays.stream(arr)
                               .sum()) / size;
    }
}
