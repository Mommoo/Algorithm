package com.mommoo.level1;

public class 핸드폰_번호_가리기 {
    public static void main(String[] args) {
        System.out.println(new 핸드폰_번호_가리기().solution("01033334444"));
        System.out.println(new 핸드폰_번호_가리기().solution("027778888"));
    }

    public String solution(String phone_number) {
        int needToHideLen = phone_number.length() - 4;
        return "*".repeat(needToHideLen) + phone_number.substring(needToHideLen);
    }
}
