package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BOJ1411 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int wordCount = Integer.parseInt(reader.readLine());
        Word[] words = new Word[wordCount];
        for (int i = 0; i < wordCount; i++) {
            words[i] = new Word(reader.readLine());
        }

        int answer = 0;
        for (int i = 0; i < wordCount; i++) {
            for (int j = i + 1; j < wordCount; j++) {
                answer += words[i].isSimilar(words[j]) ? 1 : 0;
            }
        }
        System.out.println(answer);
    }

    private static class Word {
        private final String value;
        private final Map<Character, List<Integer>> indexes = new HashMap<>();

        public Word(String word) {
            this.value = word;
            for (int i = 0 ; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!indexes.containsKey(c)) {
                    indexes.put(c, new ArrayList<>());
                }

                indexes.get(c).add(i);
            }
        }

        public boolean isSimilar(Word word) {
            for (int i = 0; i < value.length(); i++) {
                if (!isEqualsIndexes(i, word)) {
                    return false;
                }
            }
            return true;
        }

        private char charAt(int index) {
            return this.value.charAt(index);
        }

        private boolean isEqualsIndexes(int index, Word word) {
            List<Integer> thisIndexes = this.indexes.get(charAt(index));
            List<Integer> otherIndexes = word.indexes.get(word.charAt(index));

            if (thisIndexes.size() != otherIndexes.size()) {
                return false;
            }

            for (int i = 0; i < thisIndexes.size(); i++) {
                if (!Objects.equals(thisIndexes.get(i), otherIndexes.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }
}
