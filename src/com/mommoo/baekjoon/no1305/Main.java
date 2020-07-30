package com.mommoo.baekjoon.no1305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int adLen = Integer.parseInt(reader.readLine());
        String fullWord = reader.readLine();
        for (int i = 0; i < adLen; i++) {
            if (isEqualSequence(i, fullWord)) {
                System.out.println(i + 1);
                return;
            }
        }
    }

    private static boolean isEqualSequence(int index, String fullWord) {
        int wordLen = index + 1;
        int nextIndex = wordLen;
        while (nextIndex <= fullWord.length()) {
            for (int i = 0; i < wordLen; i++) {
                if (nextIndex + i >= fullWord.length()) {
                    break;
                }
                if (fullWord.charAt(nextIndex + i) != fullWord.charAt(i)) {
                    return false;
                }
            }
            nextIndex += wordLen;
        }

        return true;
    }
}
