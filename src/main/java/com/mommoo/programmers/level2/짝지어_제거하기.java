package com.mommoo.programmers.level2;

import java.util.Stack;

public class 짝지어_제거하기 {
    public static void main(String[] args) {
        System.out.println(new 짝지어_제거하기().solution("baabaa"));
        System.out.println(new 짝지어_제거하기().solution("cdcd"));
    }

    public int solution(String s) {
        Stack<Character> results = new Stack<>();
        results.push(s.charAt(0));

        for (int i = 1; i < s.length() ; i++) {
            char current = s.charAt(i);

            if (!results.isEmpty() && (current == results.peek())) {
                results.pop();
                continue;
            }

            results.push(current);
        }


        return results.isEmpty() ? 1 : 0;
    }
}
