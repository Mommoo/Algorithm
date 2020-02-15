package com.mommoo.programmers.level1;

import java.util.Arrays;

public class 제일_작은_수_제거하기 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 제일_작은_수_제거하기().solution(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(new 제일_작은_수_제거하기().solution(new int[]{10})));
    }

    public int[] solution(int[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }

        int minNum = Arrays.stream(arr)
                           .min()
                           .orElse(-1);

        return Arrays.stream(arr)
                     .filter(x -> x != minNum)
                     .toArray();
    }
}
