package com.mommoo.programmers.level1;

import java.util.Arrays;

public class 나누어_떨어지는_숫자_배열 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 나누어_떨어지는_숫자_배열().solution(new int[]{5, 9, 7, 10}, 5)));
        System.out.println(Arrays.toString(new 나누어_떨어지는_숫자_배열().solution(new int[]{2, 36, 1, 3}, 1)));
        System.out.println(Arrays.toString(new 나누어_떨어지는_숫자_배열().solution(new int[]{3, 2, 6}, 10)));
    }

    public int[] solution(int[] arr, int divisor) {
        int[] answer = Arrays.stream(arr)
                             .filter(v -> v % divisor == 0)
                             .sorted()
                             .toArray();

        return answer.length == 0 ? new int[]{-1} : answer;
    }
}
