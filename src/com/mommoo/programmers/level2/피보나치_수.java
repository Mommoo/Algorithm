package com.mommoo.programmers.level2;

public class 피보나치_수 {
    public static void main(String[] args) {
        System.out.println(new 피보나치_수().solution(3));
        System.out.println(new 피보나치_수().solution(5));
    }

    public int solution(int n) {
        long[] dp = new long[100001];
        return (int) fibo(n, dp);
    }

    private long fibo(int n, long[] dp) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        if (dp[n] == 0) {
            dp[n] = fibo(n - 1, dp) + fibo(n - 2, dp);
        }

        return dp[n] % 1234567;
    }
}
