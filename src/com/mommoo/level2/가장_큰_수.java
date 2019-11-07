package com.mommoo.level2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class 가장_큰_수 {
    public static void main(String[] args) {
        System.out.println(new 가장_큰_수().solution(new int[]{6, 10, 2}));
        System.out.println(new 가장_큰_수().solution(new int[]{3, 30, 34, 5, 9}));
    }

    public String solution(int[] numbers) {
        String result = Arrays.stream(numbers)
                              .mapToObj(Integer::toString)
                              .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
                              .collect(Collectors.joining());

        return result.charAt(0) == '0' ? "0" : result;
    }
}
