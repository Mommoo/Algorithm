package com.mommoo.programmers.exam.day1;

public class Solution1 {
    public static void main(String[] args) {
        System.out.println(new Solution1().solution("100", "2345"));
    }

    public String solution(String X, String Y) {
        int[][] numberCounts = new int[2][10];

        for (int i = 0; i < X.length(); i++) {
            int number = X.charAt(i) - '0';
            numberCounts[0][number]++;
        }

        for (int i = 0; i < Y.length(); i++) {
            int number = Y.charAt(i) - '0';
            numberCounts[1][number]++;
        }

        StringBuilder builder = new StringBuilder();
        for (int number = 9; number >= 0; number--) {
            if (numberCounts[0][number] == 0 || numberCounts[1][number] == 0) {
                continue;
            }

            int count = Math.min(numberCounts[0][number], numberCounts[1][number]);

            while (count-- > 0) {
                builder.append(number);
            }
        }

        if (builder.length() == 0) {
            return "-1";
        }

        if (builder.charAt(0) == '0') {
            return "0";
        }

        return builder.toString();
    }
}
