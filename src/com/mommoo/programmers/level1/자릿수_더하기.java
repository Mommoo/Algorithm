package com.mommoo.programmers.level1;

public class 자릿수_더하기 {
    public static void main(String[] args) {
        System.out.println(new 자릿수_더하기().solution(123));
        System.out.println(new 자릿수_더하기().solution(987));
    }

    public int solution(int n) {
        return Integer.toString(n)
                      .chars()
                      .map(cInt -> cInt - '0')
                      .sum();
    }
}
