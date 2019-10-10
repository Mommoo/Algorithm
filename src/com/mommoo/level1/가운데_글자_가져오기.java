package com.mommoo.level1;

public class 가운데_글자_가져오기 {
    public static void main(String[] args) {
        String[] cases = {"abcde", "qwer"};
        System.out.println(new 가운데_글자_가져오기().solution(cases[0]));
        System.out.println(new 가운데_글자_가져오기().solution(cases[1]));
    }

    public String solution(String s) {
        int len = s.length();
        int position = len/2;

        if (len % 2 == 0) {
            return s.substring(position -1, position + 1);
        }

        return s.substring(position, position + 1);
    }
}
