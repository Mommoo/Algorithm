package com.mommoo.level1;

import java.util.Arrays;

public class 행렬의_덧셈 {
    public static void main(String[] args) {
        int[][] result = new 행렬의_덧셈().solution(new int[][]{
                new int[]{1,2},
                new int[]{2,3},
        }, new int[][]{
                new int[]{3,4},
                new int[]{5,6},
        });

        for (int[] line: result) {
            System.out.println(Arrays.toString(line));
        }
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][];
        for (int i = 0; i < arr1.length; i++) {
            int[] a1 = arr1[i];
            int[] a2 = arr2[i];
            answer[i] = new int[a1.length];
            for (int j = 0 ; j < a1.length ; j++) {
                answer[i][j] = a1[j] + a2[j];
            }
        }
        return answer;
    }
}
