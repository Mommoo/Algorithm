package com.mommoo.programmers.level3;

public class _2xn_타일링 {
    private final int[] dp = new int[60001];
    public static void main(String[] args) {
        System.out.println(new _2xn_타일링().solution(4));
    }

    public int solution(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        if (dp[n] == 0) {
            long count = 0;

            count += solution(n - 1);
            count += solution(n - 2);

            dp[n] = (int)(count % 1000000007L);
        }

        return dp[n];
    }
}
