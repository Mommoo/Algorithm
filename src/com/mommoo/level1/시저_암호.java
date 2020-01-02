package com.mommoo.level1;

import java.util.stream.Collectors;

public class 시저_암호 {
    public static void main(String[] args) {
        System.out.println(new 시저_암호().solution("AB", 1));
        System.out.println(new 시저_암호().solution("z", 1));
        System.out.println(new 시저_암호().solution("a B z", 4));
    }

    public String solution(String s, int n) {
        return new CaesarCipher(s, n).get();
    }

    private static class CaesarCipher {
        private final String value;

        private CaesarCipher(String string, int moveCount) {
            this.value = string.chars()
                               .mapToObj(ascii -> new CipherCharacter((char) ascii, moveCount))
                               .map(CipherCharacter::get)
                               .map(String::valueOf)
                               .collect(Collectors.joining());
        }

        public String get() {
            return value;
        }
    }

    private static class CipherCharacter {
        private final char value;

        public CipherCharacter(char character, int moveCount) {
            if (character == ' ') {
                this.value = ' ';
                return;
            }

            char lowerCaseValue = getLowerCaseValue(character, moveCount);

            if (Character.isUpperCase(character)) {
                this.value = Character.toUpperCase(lowerCaseValue);
            } else {
                this.value = lowerCaseValue;
            }
        }

        private static char getLowerCaseValue(char character, int moveCount) {
            character = Character.toLowerCase(character);
            int offset = (character + moveCount - 'a') % ('z' - 'a' + 1);
            return (char)('a' + offset);
        }

        public char get() {
            return this.value;
        }
    }
}
