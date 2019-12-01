package com.mommoo.level3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class 단어_변환 {
    public static void main(String[] args) {
        System.out.println(new 단어_변환().solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(new 단어_변환().solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

    private static boolean isNotContains(String target, String[] words) {
        return !Arrays.asList(words).contains(target);
    }

    private static Queue<Word> createWordQueue(String begin) {
        Word beginWord = Word.first(begin);
        Queue<Word> wordQueue = new LinkedList<>();
        wordQueue.offer(beginWord);
        return wordQueue;
    }

    public int solution(String begin, String target, String[] words) {
        if (isNotContains(target, words)) {
            return 0;
        }

        int wordLen = words.length;
        boolean[][] visit = new boolean[wordLen + 1][wordLen + 1];

        Queue<Word> wordQueue = createWordQueue(begin);

        while(!wordQueue.isEmpty()) {
            Word word = wordQueue.poll();

            for (int i = 0 ; i < wordLen ; i++) {
                int visitIndex = i + 1;
                if (!visit[word.index][visitIndex] && word.isPossibleNextWord(words[i])) {
                    Word nextWord = word.nextWord(visitIndex, words[i]);
                    if (nextWord.isEquals(target)) {
                        return nextWord.progress;
                    }
                    wordQueue.offer(nextWord);
                    visit[word.index][visitIndex] = visit[visitIndex][word.index] = true;
                }
            }
        }

        return 0;
    }

    private static class Word {
        private final int index;
        private final int progress;
        private final String string;

        public Word(int index, int progress, String string) {
            this.index = index;
            this.progress = progress;
            this.string = string;
        }

        public static Word first(String begin) {
            return new Word(0, 0, begin);
        }

        public boolean isPossibleNextWord(String string) {
            int differentCount = 0;
            for (int i = 0 ; i < string.length() ; i++) {
                char source = this.string.charAt(i);
                char target = string.charAt(i);

                if (source != target) {
                    differentCount++;
                }

                if (differentCount > 1) {
                    return false;
                }
            }

            return differentCount == 1;
        }

        public Word nextWord(int index, String string) {
            return new Word(index, this.progress + 1, string);
        }

        public boolean isEquals(String target) {
            return Objects.equals(this.string, target);
        }
    }
}
