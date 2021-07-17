package com.mommoo.baekjoon.no1406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Editor editor = new Editor();
        editor.append(reader.readLine());
        int commandCount = Integer.parseInt(reader.readLine());

        while (commandCount -- > 0) {
            String command = reader.readLine();
            char operation = command.charAt(0);

            switch (operation) {
                case 'P':
                    editor.appendAtCursorLeft(command.charAt(2));
                    break;

                case 'L':
                    editor.moveCursorToLeft();
                    break;

                case 'D':
                    editor.moveCursorToRight();
                    break;

                case 'B':
                    editor.backspace();
                    break;

                default:
                    break;
            }
        }

        System.out.println(editor.toString());
    }

    private static class Editor {
        private final Deque<Character> mainDeque = new LinkedList<>();
        private final Deque<Character> cursorRightDeque = new LinkedList<>();

        public void append(String sentence) {
            int sentenceLen = sentence.length();
            for (int i = 0; i < sentenceLen; i++) {
                mainDeque.push(sentence.charAt(i));
            }
        }

        public void moveCursorToLeft() {
            if (!mainDeque.isEmpty()) {
                cursorRightDeque.push(mainDeque.pop());
            }
        }

        public void moveCursorToRight() {
            if (!cursorRightDeque.isEmpty()) {
                mainDeque.push(cursorRightDeque.pop());
            }
        }

        public void backspace() {
            if (mainDeque.isEmpty()) {
                return;
            }

            mainDeque.poll();
        }

        public void appendAtCursorLeft(char c) {
            mainDeque.push(c);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            while (!mainDeque.isEmpty()) {
                builder.append(mainDeque.pollLast());
            }

            while (!cursorRightDeque.isEmpty()) {
                builder.append(cursorRightDeque.pop());
            }
            return builder.toString();
        }
    }
}
