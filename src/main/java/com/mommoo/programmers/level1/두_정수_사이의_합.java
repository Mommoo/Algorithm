package com.mommoo.programmers.level1;

import java.util.stream.LongStream;

public class 두_정수_사이의_합 {
    public static void main(String[] args) {
        System.out.println(new 두_정수_사이의_합().solution(3, 5));
        System.out.println(new 두_정수_사이의_합().solution(3, 3));
        System.out.println(new 두_정수_사이의_합().solution(5, 3));
    }

    public long solution(int a, int b) {
        int maxNum = Math.max(a, b);
        int minNum = Math.min(a, b);
        if (maxNum == minNum) {
            return maxNum;
        }

        return LongStream.rangeClosed(minNum, maxNum)
                         .sum();
    }
}
