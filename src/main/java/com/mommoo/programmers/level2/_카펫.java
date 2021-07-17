package com.mommoo.programmers.level2;

import java.util.Arrays;

public class _카펫 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _카펫().solution(10, 2)));
        System.out.println(Arrays.toString(new _카펫().solution(8, 1)));
        System.out.println(Arrays.toString(new _카펫().solution(24, 24)));
    }

    public int[] solution(int brown, int yellow) {
        int rowCount = 1;

        while (rowCount <= yellow) {
            if (yellow % rowCount == 0 && brown == computeBrownCount(rowCount, yellow / rowCount)) {
                return new int[]{(yellow / rowCount) + 2, rowCount + 2};
            }

            rowCount ++;
        }

        throw new RuntimeException();
    }

    private static int computeBrownCount(int rowYellow, int colYellow) {
        return colYellow * 2 + rowYellow * 2 + 4;
    }
}
