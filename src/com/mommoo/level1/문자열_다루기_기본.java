package com.mommoo.level1;

import java.util.Optional;

public class 문자열_다루기_기본 {
    public static void main(String[] args) {
        System.out.println(new 문자열_다루기_기본().solution("a234"));
        System.out.println(new 문자열_다루기_기본().solution("1234"));
    }

    public boolean solution(String s) {
        return Optional.of(s)
                       .filter(string -> s.length() == 4 || s.length() == 6)
                       .map(string -> s.chars())
                       .map(intStream -> intStream.allMatch(c -> '0' <= c && c <= '9'))
                       .orElse(false);
    }
}
