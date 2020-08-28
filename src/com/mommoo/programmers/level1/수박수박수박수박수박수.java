package com.mommoo.programmers.level1;

public class 수박수박수박수박수박수 {
    public static void main(String[] args) {
        System.out.println(new 수박수박수박수박수박수().solution(3));
        System.out.println(new 수박수박수박수박수박수().solution(4));
    }

    public String solution(int n) {
        int repeat = n / 2;
        StringBuilder builder = new StringBuilder();
        while (repeat-- > 0) {
            builder.append("수박");
        }
        return builder.toString() + (n % 2 == 1 ? "수" : "");
    }
}
