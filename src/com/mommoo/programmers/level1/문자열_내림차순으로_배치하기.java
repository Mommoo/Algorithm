package com.mommoo.programmers.level1;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 문자열_내림차순으로_배치하기 {
    public static void main(String[] args) {
        System.out.println(new 문자열_내림차순으로_배치하기().solution("Zbcdefg"));
    }

    public String solution(String s) {
        return Stream.of(s.split(""))
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.joining());
    }
}
