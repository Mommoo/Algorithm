package com.mommoo.baekjoon.no3059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();
    private static final boolean[] ALPHABET = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(reader.readLine());

        while (TC-- > 0) {
            Arrays.fill(ALPHABET, false);
            fillUsedAlphabet(reader.readLine());
            BUILDER.append(computeNotUsedAlphabetCodeSum())
                   .append('\n');
        }

        reader.close();
        System.out.println(BUILDER);
    }

    private static void fillUsedAlphabet(String alphabets) {
        for (int i = 0; i < alphabets.length(); i++) {
            int alphabetIndex = alphabets.charAt(i) - 'A';
            ALPHABET[alphabetIndex] = true;
        }
    }

    private static int computeNotUsedAlphabetCodeSum() {
        int codeSum = 0;

        for (int i = 0 ; i < 26; i++) {
            if (ALPHABET[i]) {
                continue;
            }

            int alphabetCode = i + 'A';
            codeSum += alphabetCode;
        }

        return codeSum;
    }
}
