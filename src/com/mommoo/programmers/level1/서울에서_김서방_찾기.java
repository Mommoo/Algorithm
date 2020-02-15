package com.mommoo.programmers.level1;

import java.util.stream.IntStream;

public class 서울에서_김서방_찾기 {
    public static void main(String[] args) {
        System.out.println(new 서울에서_김서방_찾기().solution(new String[]{"Jane", "Kim"}));
    }

    public String solution(String[] seoul) {
        int index = IntStream.range(0, seoul.length)
                             .filter(idx -> seoul[idx].equals("Kim"))
                             .findFirst()
                             .orElse(-1);

        return String.format("김서방은 %d에 있다", index);
    }
}
