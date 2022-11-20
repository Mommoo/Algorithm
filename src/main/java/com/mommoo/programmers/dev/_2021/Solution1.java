package com.mommoo.programmers.dev._2021;

public class Solution1 {
    public int[] solution(int[] lottos, int[] win_nums) {
        boolean[] matchers = createMatchers(win_nums);
        int matchCount = 0;
        int unknownCount = 0;

        for (int lottoNum: lottos) {
            if (lottoNum == 0) {
                ++unknownCount;
                continue;
            }

            if (matchers[lottoNum]) {
                ++matchCount;
            }
        }

        int minGrade = Math.min(6, 7 - matchCount);
        int maxGrade = Math.min(6, 7 - (matchCount + unknownCount));

        return new int[]{maxGrade, minGrade};
    }

    private static boolean[] createMatchers(int[] winNums) {
        boolean[] matchers = new boolean[46];

        for (int winNum : winNums) {
            matchers[winNum]= true;
        }

        return matchers;
    }
}
