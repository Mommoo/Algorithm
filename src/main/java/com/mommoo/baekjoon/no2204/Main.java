package com.mommoo.baekjoon.no2204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();

        PriorityQueue<Word> wordPriorityQueue = new PriorityQueue<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String number;
        while (!(number = reader.readLine()).equals("0")) {
            wordPriorityQueue.clear();

            int count = Integer.parseInt(number);
            while (count-- > 0) {
                String string = reader.readLine();

                wordPriorityQueue.offer(new Word(string));
            }

            builder.append(wordPriorityQueue.poll().value)
                   .append('\n');
        }
        reader.close();
        System.out.println(builder);
    }

    private static class Word implements Comparable<Word> {
        private final String value;

        public Word(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(Word o) {
            return value.toLowerCase().compareTo(o.value.toLowerCase());
        }
    }
}
