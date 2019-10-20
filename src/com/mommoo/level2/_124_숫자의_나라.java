package com.mommoo.level2;

public class _124_숫자의_나라 {

    public static void main(String[] args) {
        for (int i = 1 ; i < 11 ; i ++) {
            System.out.println(new _124_숫자의_나라().solution(i));
        }
    }

    public String solution(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            int quotient = n / 3;
            int remainder = n % 3;

            if (remainder == 0) {
                quotient -= 1;
            }

            n = quotient;

            int value = remainder == 0 ? 4 : remainder;
            stringBuilder.insert(0, value);
        }

        return stringBuilder.toString();
    }
}
