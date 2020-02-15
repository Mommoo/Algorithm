package com.mommoo.programmers.level3;

public class 멀리_뛰기 {
    public static void main(String[] args) {
        System.out.println(new 멀리_뛰기().solution(4));
        System.out.println(new 멀리_뛰기().solution(3));
    }

    public long solution(int n) {
        if (n <= 2) {
            return n;
        }

        long[] dp = new long[n + 1];
        dp[1] = 1; dp[2] = 2;

        for (int i = 3 ; i < n + 1; i ++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567;
        }

        return dp[n];
    }

//    public long solution(int n) {
//        return dfs(n) % 1234567;
//    }
//
//    private long dfs(int remain) {
//        if (remain == 0) {
//            return 1L;
//        } else if (remain < 0) {
//            return 0L;
//        }
//
//        int count = 0 ;
//        count += dfs(remain - 1);
//        count += dfs(remain - 2);
//
//        return count;
//    }
}
