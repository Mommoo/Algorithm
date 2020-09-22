package com.mommoo.baekjoon.no2596;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static StringBuilder BUILDER = new StringBuilder();
    private static final Map<Character, SecretCode> SECRET_CODES = createSecretCodes();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int letterCount = Integer.parseInt(reader.readLine());
        String codeStrings = reader.readLine();
        for (int i = 0 ; i < letterCount; i++) {
            String code = codeStrings.substring(i * 6, i * 6 + 6);
            Character letter = findLetter(code);
            if (letter == null) {
                System.out.println(i + 1);
                return;
            }

            BUILDER.append(letter);
        }

        System.out.println(BUILDER);
    }

    private static Map<Character, SecretCode> createSecretCodes() {
        Map<Character, SecretCode> codes = new HashMap<>();
        codes.put('A', new SecretCode("000000"));
        codes.put('B', new SecretCode("001111"));
        codes.put('C', new SecretCode("010011"));
        codes.put('D', new SecretCode("011100"));
        codes.put('E', new SecretCode("100110"));
        codes.put('F', new SecretCode("101001"));
        codes.put('G', new SecretCode("110101"));
        codes.put('H', new SecretCode("111010"));
        return codes;
    }

    private static Character findLetter(String code) {
        int minDiff = Integer.MAX_VALUE;

        for (Character letter : SECRET_CODES.keySet()) {
            int diffCount = SECRET_CODES.get(letter).countDifferentCode(code);
            if (diffCount == 0) {
                return letter;
            }

            minDiff = Math.min(minDiff, diffCount);
        }

        Character foundLetter = null;
        for (Character letter : SECRET_CODES.keySet()) {
            int diffCount = SECRET_CODES.get(letter).countDifferentCode(code);
            if (diffCount != minDiff) {
                continue;
            }

            if (foundLetter != null) {
                return null;
            } else {
                foundLetter = letter;
            }
        }

        return foundLetter;
    }

    private static class SecretCode {
        private final String code;

        public SecretCode(String code) {
            this.code = code;
        }

        public int countDifferentCode(String code) {
            int diffCount = 0;

            for (int i = 0 ; i < 6; i++) {
                if (this.code.charAt(i) != code.charAt(i)) {
                    diffCount++;
                }
            }

            return diffCount;
        }
    }
}
