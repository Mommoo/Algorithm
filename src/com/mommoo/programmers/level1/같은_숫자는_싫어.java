package com.mommoo.programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 같은_숫자는_싫어 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 같은_숫자는_싫어().solution(new int[]{1, 1, 3, 3, 0, 1, 1})));
        System.out.println(Arrays.toString(new 같은_숫자는_싫어().solution(new int[]{4, 4, 4, 3, 3})));
    }

    public int[] solution(int[] arr) {
        List<Integer> answers = new ArrayList<>();
        int previousValue = -1;
        for (int value : arr) {
            if (previousValue == value) {
                continue;
            }
            answers.add(value);
            previousValue = value;
        }

        return answers.stream()
                      .mapToInt(x -> x)
                      .toArray();
    }
}
