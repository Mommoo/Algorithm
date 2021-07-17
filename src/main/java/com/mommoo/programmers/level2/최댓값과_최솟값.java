package com.mommoo.programmers.level2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 최댓값과_최솟값 {
    public static void main(String[] args) {
        System.out.println(new 최댓값과_최솟값().solution("1 2 3 4"));
        System.out.println(new 최댓값과_최솟값().solution("-1 -2 -3 -4"));
        System.out.println(new 최댓값과_최솟값().solution("-1 -1"));
    }

    public String solution(String s) {
        List<Integer> numbers = Arrays.stream(s.split(" "))
                                      .map(Integer::parseInt)
                                      .sorted()
                                      .collect(Collectors.toList());
        return numbers.get(0) + " " + numbers.get(numbers.size() - 1);
    }


}
