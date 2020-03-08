package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ1371 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Letter> letters = createAlphabets();

        String documentLine;
        while ((documentLine = reader.readLine()) != null) {
            countAlphabets(letters, documentLine);
        }
        reader.close();

        System.out.println(joiningMaxCountLetters(letters));
    }

    private static List<Letter> createAlphabets() {
        List<Letter> letters = new ArrayList<>();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            letters.add(new Letter(alphabet));
        }
        return letters;
    }

    private static void countAlphabets(List<Letter> letters, String documentLine) {
        documentLine.chars()
                    .map(intChar -> intChar - 'a')
                    .filter(idx -> 0 <= idx && idx < letters.size())
                    .mapToObj(letters::get)
                    .forEach(Letter::increaseCount);
    }

    private static String joiningMaxCountLetters(List<Letter> letters) {
        Collections.sort(letters);
        int maxCount = letters.get(0).count;

        StringBuilder builder = new StringBuilder();

        for (Letter letter : letters) {
            if (maxCount != letter.count) {
                break;
            }

            builder.append(letter.value);
        }

        return builder.toString();
    }

    private static class Letter implements Comparable<Letter> {
        private final char value;
        private int count;

        public Letter(char value) {
            this.value = value;
        }

        public void increaseCount() {
            this.count++;
        }

        @Override
        public int compareTo(Letter o) {
            if (this.count == o.count) {
                return this.value - o.value;
            }

            return o.count - this.count;
        }
    }
}
