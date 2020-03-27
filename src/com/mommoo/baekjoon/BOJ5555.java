package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5555 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String findString = reader.readLine();
        int ringCount = Integer.parseInt(reader.readLine());

        int containCount = 0;
        while (ringCount-- > 0) {
            String ringString = reader.readLine();
            containCount += isContainString(findString, ringString) ? 1 : 0;
        }

        System.out.println(containCount);
    }

    public static boolean isContainString(String findString, String ringString) {
        int beginIndex = 0;
        char beginFindStringChar = findString.charAt(0);

        while (beginIndex < ringString.length()) {
            beginIndex = ringString.indexOf(beginFindStringChar, beginIndex);
            if (beginIndex == -1) {
                return false;
            }

            if (checkContainString(findString, ringString, beginIndex)) {
                return true;
            }

            beginIndex++;
        }

        return false;
    }

    public static boolean checkContainString(String findString, String ringString, int beginIndex) {
        for (int i = 0 ; i < findString.length(); i++) {
            char ringStringChar = ringString.charAt(beginIndex++);
            char findStringChar = findString.charAt(i);

            if (ringStringChar != findStringChar) {
                return false;
            }

            if (beginIndex == ringString.length()) {
                beginIndex = 0;
            }
        }

        return true;
    }
}
