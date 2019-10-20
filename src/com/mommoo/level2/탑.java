package com.mommoo.level2;

import java.util.Arrays;

public class 탑 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 탑().solution(new int[]{6, 9, 5, 7, 4})));
        System.out.println(Arrays.toString(new 탑().solution(new int[]{3, 9, 9, 3, 5, 7, 2})));
        System.out.println(Arrays.toString(new 탑().solution(new int[]{1, 5, 3, 6, 7, 6, 5})));
    }

    public int[] solution(int[] heights) {
        int[] answer = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            for (int j = i - 1 ; j >= 0 ; j--) {
                if (heights[j] > heights[i]) {
                    answer[i] = j + 1;
                    break;
                }
            }
        }

        return answer;
    }
}
