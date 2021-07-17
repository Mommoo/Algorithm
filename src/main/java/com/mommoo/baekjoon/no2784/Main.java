package com.mommoo.baekjoon.no2784;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {
    private static final StringBuilder TEMP_BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        List<String> words = createWords();

        List<Puzzle> puzzles = new ArrayList<>();
        dfs(puzzles, words, new boolean[6], new String[3], 0);

        removeInvalidPuzzle(puzzles, words);
        Collections.sort(puzzles);

        if (puzzles.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(puzzles.get(0));
        }
    }

    private static void dfs(List<Puzzle> puzzles, List<String> words, boolean[] choices, String[] choiceWords, int nextIndex) {
        if (nextIndex == 3) {
            puzzles.add(new Puzzle(choiceWords));
            return;
        }

        for (int i = 0 ; i < 6; i++) {
            if (choices[i]) {
                continue;
            }

            choiceWords[nextIndex] = words.get(i);
            choices[i] = true;
            dfs(puzzles, words, choices, choiceWords,  nextIndex + 1);
            choiceWords[nextIndex] = null;
            choices[i] = false;
        }
    }

    private static void removeInvalidPuzzle(List<Puzzle> puzzles, List<String> words) {
        Iterator<Puzzle> iterator = puzzles.iterator();
        while (iterator.hasNext()) {
            Puzzle puzzle = iterator.next();
            if (puzzle.isValid(words)) {
                continue;
            }

            iterator.remove();
        }
    }

    private static List<String> createWords() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> words = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            words.add(reader.readLine());
        }

        reader.close();

        return words;
    }

    private static class Puzzle implements Comparable<Puzzle> {
        private final String[] words;

        public Puzzle(String[] words) {
            this.words = Arrays.copyOf(words, words.length);
        }

        public boolean isValid(List<String> words) {
            boolean[] checker = new boolean[6];
            for (int i = 0; i < 3; i++) {
                String columnWord = this.words[i];
                int findIndex = findWord(columnWord, checker, words);
                if (findIndex == -1) {
                    return false;
                }
                checker[findIndex] = true;

                String rowWord = computeRowWord(i);
                findIndex = findWord(rowWord, checker, words);
                if (findIndex == -1) {
                    return false;
                }
                checker[findIndex] = true;
            }

            return allChecked(checker);
        }

        private int findWord(String word, boolean[] checker, List<String> words) {
            for (int i = 0; i < 6; i++) {
                if (checker[i]) {
                    continue;
                }

                if (words.get(i).equals(word)) {
                    return i;
                }
            }

            return -1;
        }

        private boolean allChecked(boolean[] checker) {
            for (boolean check : checker) {
                if (!check) {
                    return false;
                }
            }

            return true;
        }

        public String computeRowWord(int columnIndex) {
            TEMP_BUILDER.setLength(0);
            return TEMP_BUILDER.append(this.words[0].charAt(columnIndex))
                               .append(this.words[1].charAt(columnIndex))
                               .append(this.words[2].charAt(columnIndex))
                               .toString();
        }

        private String computeOneLineWords() {
            TEMP_BUILDER.setLength(0);
            return TEMP_BUILDER.append(this.words[0]).append(this.words[1]).append(this.words[2]).toString();
        }

        @Override
        public int compareTo(Puzzle o) {
            return computeOneLineWords().compareTo(o.computeOneLineWords());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Puzzle))
                return false;
            Puzzle puzzle = (Puzzle) o;

            return Arrays.equals(words, puzzle.words);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(words);
        }

        @Override
        public String toString() {
            return words[0] + "\n" + words[1] + "\n" + words[2];
        }
    }
}
