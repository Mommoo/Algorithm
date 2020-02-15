package com.mommoo.programmers.level1;

public class 문자열_내_p와_y의_개수 {
    public static void main(String[] args) {
        System.out.println(new 문자열_내_p와_y의_개수().solution("pPoooyY"));
        System.out.println(new 문자열_내_p와_y의_개수().solution("Pyy"));
    }

    boolean solution(String s) {
        int count = 0;

        for (int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);
            char lowercaseC = Character.toLowerCase(c);

            if (lowercaseC == 'p') {
                count ++;
            } else if (lowercaseC == 'y') {
                count --;
            }
        }

        return count == 0;
    }
}
