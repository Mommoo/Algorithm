package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class BOJ10798 {
    public static void main(String[] args) throws Exception {
        LinkedList<CharBuffer> charBuffers = createCharBuffers();

        StringBuilder stringBuilder = new StringBuilder();
        while (!charBuffers.isEmpty()) {
            Iterator<CharBuffer> charBufferIterator = charBuffers.iterator();
            while (charBufferIterator.hasNext()) {
                CharBuffer charBuffer = charBufferIterator.next();
                stringBuilder.append(charBuffer.poll());
                if (charBuffer.isEmpty()) {
                    charBufferIterator.remove();
                }
            }
        }
        System.out.println(stringBuilder);
    }

    private static LinkedList<CharBuffer> createCharBuffers() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<CharBuffer> charBuffers = new LinkedList<>();
        int N = 5;
        while (N -- > 0) {
            String string = reader.readLine();
            charBuffers.add(new CharBuffer(string));
        }
        return charBuffers;
    }

    private static class CharBuffer {
        private final LinkedList<Character> characters;

        public CharBuffer(String string) {
            this.characters = string.chars()
                                    .mapToObj(intChar -> (char) intChar)
                                    .collect(Collectors.toCollection(LinkedList::new));
        }

        public boolean isEmpty() {
            return characters.isEmpty();
        }

        public Character poll() {
            return this.characters.poll();
        }
    }
}
