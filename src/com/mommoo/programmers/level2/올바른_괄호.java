package com.mommoo.programmers.level2;

public class 올바른_괄호 {
    public static void main(String[] args) {
        System.out.println(new 올바른_괄호().solution("()()"));
        System.out.println(new 올바른_괄호().solution("(())()"));
        System.out.println(new 올바른_괄호().solution(")()("));
        System.out.println(new 올바른_괄호().solution("(()("));
    }

    boolean solution(String s) {
        int left = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            if (s.charAt(idx) == '(') {
                left++;
            } else {
                left--;
            }

            if (left == -1) {
                return false;
            }
        }

        return left == 0;
    }
}
