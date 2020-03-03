package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class BOJ1181 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        SortedSet<Word> words = new TreeSet<>();
        while (N -- > 0) {
            words.add(new Word(reader.readLine()));
        }

        for (Word word: words) {
            System.out.println(word.value);
        }
    }

    private static class Word implements Comparable<Word> {
        public final String value;

        public Word(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(Word o) {
            if (this.value.length() == o.value.length()) {
                return this.value.compareTo(o.value);
            }

            return this.value.length() - o.value.length();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Word))
                return false;
            Word word = (Word) o;
            return Objects.equals(value, word.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
