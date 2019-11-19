package com.mommoo.level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class 전화번호_목록 {
    public static void main(String[] args) {
        System.out.println(new 전화번호_목록().solution(new String[]{"119", "97674223", "1195524421"}));
        System.out.println(new 전화번호_목록().solution(new String[]{"123", "456", "789"}));
        System.out.println(new 전화번호_목록().solution(new String[]{"12", "123", "1235", "567", "88"}));
    }

    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, Comparator.comparingInt(String::length));
        int len = phone_book.length;
        for (int i = 0; i < len; i++) {
            String findText = phone_book[i];
            int findTextPosition = i;
            boolean isAnyMatch = IntStream.range(0, len)
                                           .filter(idx -> idx != findTextPosition)
                                           .mapToObj(idx -> phone_book[idx])
                                           .anyMatch(targetText -> isPrefixMatchString(findText, targetText));
            if (isAnyMatch) {
                return false;
            }
        }
        return true;
    }

    private boolean isPrefixMatchString(String findText, String targetText) {
        for (int i = 0; i < findText.length(); i++) {
            if (findText.charAt(i) != targetText.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
