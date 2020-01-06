package com.mommoo.level1;

public class 문자열을_정수로_바꾸기 {
    public static void main(String[] args) {
        System.out.println(new 문자열을_정수로_바꾸기().solution("1234"));
        System.out.println(new 문자열을_정수로_바꾸기().solution("+1234"));
        System.out.println(new 문자열을_정수로_바꾸기().solution("-1234"));
    }

    public int solution(String s) {
        return new NumberString(s).get();
    }

    private static class NumberString {
        private final int operator;
        private final int value;

        public NumberString(String s) {
            String firstString = s.substring(0, 1);
            String remainString = "";
            if (firstString.equals("-")) {
                this.operator = -1;
                remainString = s.substring(1);
            } else if (firstString.equals("+")) {
                this.operator = +1;
                remainString = s.substring(1);
            } else {
                this.operator = +1;
                remainString = s;
            }
            this.value = Integer.parseInt(remainString);
        }

        public int get() {
            return operator * value;
        }
    }
}
