package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1543 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String document = reader.readLine();
        String findWord = reader.readLine();
        reader.close();

        int count = 0;

        int currentIndex = 0;
        while (currentIndex < document.length()) {
            int findIndex = document.indexOf(findWord, currentIndex);

            if (findIndex == -1) {
                break;
            }

            currentIndex = findIndex + findWord.length();
            count++;
        }

        System.out.println(count);
    }
}
