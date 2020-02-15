package com.mommoo.programmers.level1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 자연수_뒤집어_배열로_만들기 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 자연수_뒤집어_배열로_만들기().solution(12345)));
    }

    public int[] solution(long n) {
        String num = String.valueOf(n);
        int len = num.length();
        return IntStream.range(0, len)
                        .map(idx -> len - idx - 1)
                        .map(reverseIdx -> num.charAt(reverseIdx) - '0')
                        .toArray();
    }
}
