package com.mommoo.level1;

public class 정수_제곱근_판별 {
    public static void main(String[] args) {
        System.out.println(new 정수_제곱근_판별().solution(121));
        System.out.println(new 정수_제곱근_판별().solution(3));
    }

    public long solution(long n) {
        long nextNum = 1;
        while (Math.pow(nextNum, 2) <= n) {
            nextNum++;
        }

        return (long) (Math.pow(nextNum - 1, 2) == n ? Math.pow(nextNum, 2) : -1);
    }
}
