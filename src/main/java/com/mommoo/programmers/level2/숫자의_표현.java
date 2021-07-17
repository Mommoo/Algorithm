package com.mommoo.programmers.level2;

public class 숫자의_표현 {
    public static void main(String[] args) {
        System.out.println(new 숫자의_표현().solution(15));
        System.out.println(new 숫자의_표현().solution(10000));
    }

    public int solution(int n) {
        int answer = 0;

        for (int beginNum = 1; beginNum <= n; beginNum++) {
            int sum = 0;
            int nextNum = beginNum;
            while (sum < n) {
                sum += nextNum;
                nextNum++;
            }

            if (sum == n) {
                answer++;
            }
        }
        return answer;
    }
}
