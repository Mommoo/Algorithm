package com.mommoo.level1;

import java.util.Arrays;

public class 문자열_내_마음대로_정렬하기 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 문자열_내_마음대로_정렬하기().solution(new String[]{"sun", "bed", "car"}, 1)));
        System.out.println(Arrays.toString(new 문자열_내_마음대로_정렬하기().solution(new String[]{"abce", "abcd", "cdx"}, 2)));
    }

    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (s1, s2) -> compare(s1, s2, n));
        return strings;
    }

    private static int compare(String s1, String s2, int n) {
        char c1 = s1.charAt(n);
        char c2 = s2.charAt(n);

        if (c1 == c2) {
            return s1.compareTo(s2);
        }

        return Character.compare(c1, c2);
    }
}
