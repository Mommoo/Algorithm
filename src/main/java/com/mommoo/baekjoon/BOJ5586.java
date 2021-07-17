package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5586 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sentence = reader.readLine();
        System.out.println(countWord(sentence, "JOI"));
        System.out.println(countWord(sentence, "IOI"));
    }

    private static int countWord(String origin, String findWord) {
        int count = 0;

        int findIndex = origin.indexOf(findWord, 0);
        while (findIndex != -1) {
            count++;
            findIndex = origin.indexOf(findWord, findIndex + 1);
        }

        return count;
    }
}
