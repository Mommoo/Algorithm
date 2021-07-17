package com.mommoo.programmers.level1;

import java.util.Arrays;
import java.util.stream.LongStream;

public class x만큼_간격이_있는_n개의_숫자 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new x만큼_간격이_있는_n개의_숫자().solution(2, 5)));
        System.out.println(Arrays.toString(new x만큼_간격이_있는_n개의_숫자().solution(4, 3)));
        System.out.println(Arrays.toString(new x만큼_간격이_있는_n개의_숫자().solution(-4, 2)));
    }

    public long[] solution(int x, int n) {
        return LongStream.rangeClosed(1, n)
                         .map(idx -> idx * x)
                         .toArray();
    }
}
