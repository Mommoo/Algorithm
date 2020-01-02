package com.mommoo.level1;

import java.util.Arrays;
import java.util.stream.Collectors;

public class 이상한_문자_만들기 {
    public static void main(String[] args) {
        System.out.println(new 이상한_문자_만들기().solution("  try  hello   world  "));
    }

    public String solution(String s) {
        return Arrays.stream(s.split(" "))
                     .map(this::convertStrangeString)
                     .collect(Collectors.joining(" ")) + computeLastBlank(s);
    }

    private String convertStrangeString(String string) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (i % 2 == 0) {
                builder.append(Character.toUpperCase(c));
            } else {
                builder.append(Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }

    private String computeLastBlank(String string) {
        int lastBlankLength = string.length() - string.stripTrailing().length();

        StringBuilder builder = new StringBuilder();
        while (lastBlankLength-- > 0) {
            builder.append(" ");
        }

        return builder.toString();
    }
}
