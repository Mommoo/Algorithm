package com.mommoo.baekjoon.no1294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int wordCount = Integer.parseInt(reader.readLine());
        PriorityQueue<WordQueue> priorityQueue = new PriorityQueue<>();

        for (int i = 0 ; i < wordCount; i++) {
            WordQueue queue = new WordQueue(reader.readLine());
            priorityQueue.add(queue);
        }

        StringBuilder builder = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            WordQueue queue = priorityQueue.poll();
            builder.append(queue.poll());
            if (queue.isEmpty()) {
                priorityQueue.remove(queue);
            } else {
                priorityQueue.offer(queue);
            }
        }
        System.out.println(builder);
    }

    private static class WordQueue implements Comparable<WordQueue> {
        private final String word;
        private int index = 0;

        public WordQueue(String word) {
            this.word = word;
        }

        public boolean isEmpty() {
            return word.length() == index;
        }

        public char peek() {
            return word.charAt(index);
        }

        public int length() {
            return word.length() - index;
        }

        public char poll() {
            return word.charAt(index++);
        }

        public String toString() {
            return word.substring(index);
        }

        @Override
        public int compareTo(WordQueue o) {
            int minLen = Math.min(this.length(), o.length());
            int minCharacter = 99999;
            for (int i = 0; i < minLen; i++) {
                char c1 = this.word.charAt(this.index + i);
                char c2 = o.word.charAt(o.index + i);

                if (c1 != c2) {
                    return Character.compare(c1, c2);
                }

                minCharacter = Math.min(minCharacter, c1);
            }

            String maxWord = this.length() > o.length() ? this.toString() : o.toString();
            boolean thisIsMaxWord = this.length() > o.length();
            String remain = maxWord.substring(minLen);

            for (int i = 0; i < remain.length(); i++) {
                char c = remain.charAt(i);
                if (c > minCharacter) {
                    return thisIsMaxWord ? 1 : -1;
                }

                if (c < minCharacter) {
                    return thisIsMaxWord ? -1 : 1;
                }
            }

            return 0;
        }
    }
}
