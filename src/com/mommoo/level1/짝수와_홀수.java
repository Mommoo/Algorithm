package com.mommoo.level1;

public class 짝수와_홀수 {
    public static void main(String[] args) {
        System.out.println(new 짝수와_홀수().solution(3));
        System.out.println(new 짝수와_홀수().solution(4));
    }

    public String solution(int num) {
        return num % 2 == 0 ? "Even" : "Odd";
    }
}
