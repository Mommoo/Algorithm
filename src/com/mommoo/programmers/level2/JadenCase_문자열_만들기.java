package com.mommoo.programmers.level2;

public class JadenCase_문자열_만들기 {
    public static void main(String[] args) {
        System.out.println(new JadenCase_문자열_만들기().solution("3people unFollowed me"));
        System.out.println(new JadenCase_문자열_만들기().solution("for the last week"));
        System.out.println(new JadenCase_문자열_만들기().solution("for    the    last week"));
        System.out.println(new JadenCase_문자열_만들기().solution("   "));
    }

    public String solution(String s) {
        StringBuilder builder = new StringBuilder();
        builder.append(Character.toUpperCase(s.charAt(0)));

        int nextIndex = 1;
        while (nextIndex < s.length()) {
            char c = s.charAt(nextIndex);

            if (c != ' ') {
                builder.append(Character.toLowerCase(c));
                nextIndex++;
                continue;
            }

            while (c == ' ' && nextIndex < s.length()) {
                c = s.charAt(nextIndex);
                builder.append(c != ' ' ? Character.toUpperCase(c) : ' ');
                nextIndex++;
            }
        }

        return builder.toString();
    }
}
