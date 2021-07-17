package com.mommoo.programmers.level2;

public class 점프와_순간_이동 {
    public static void main(String[] args) {
        System.out.println(new 점프와_순간_이동().solution(5));
        System.out.println(new 점프와_순간_이동().solution(6));
        System.out.println(new 점프와_순간_이동().solution(5000));
    }

    public int solution(int n) {
        int ans = 0;

        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2;
                continue;
            }

            ans++;
            n--;
        }

        return ans;
    }
}
