package com.mommoo.programmers.level1;

public class 수박수박수박수박수박수 {
    public static void main(String[] args) {
        System.out.println(new 수박수박수박수박수박수().solution(3));
        System.out.println(new 수박수박수박수박수박수().solution(4));
    }

    public String solution(int n) {
        return "수박".repeat(n / 2) + (n % 2 == 1 ? "수" : "");
    }
}
