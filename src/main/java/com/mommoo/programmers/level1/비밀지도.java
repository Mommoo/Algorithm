package com.mommoo.programmers.level1;

import java.util.Arrays;

public class 비밀지도 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 비밀지도().solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28})));
    }

    public String[] solution(int n, int[] arr1, int[] arr2) {
        StringBuilder builder = new StringBuilder();
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            int value = arr1[i] | arr2[i];
            String binary = Integer.toBinaryString(value);

            builder.setLength(0);
            for (int j = binary.length() - 1; j >= 0; j--) {
                builder.append(binary.charAt(j) == '0' ? ' ' : '#');
            }

            int remainLen = n - binary.length();
            while (remainLen-- > 0) {
                builder.append(' ');
            }

            answer[i] = builder.reverse().toString();
        }
        return answer;
    }
}
