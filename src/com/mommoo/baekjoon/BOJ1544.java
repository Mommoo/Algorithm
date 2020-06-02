package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

public class BOJ1544 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        LinkedList<String> words = new LinkedList<>();
        while (N -- > 0) {
            words.offer(br.readLine());
        }

        while (!words.isEmpty()) {
            removeCircularWord(words, words.removeFirst());
            answer++;
        }

        System.out.println(answer);
    }

    private static void removeCircularWord(LinkedList<String> words, String word) {
        LinkedList<Character> chars = new LinkedList<>();
        for (int i = 0 ; i < word.length(); i++) {
            chars.offer(word.charAt(i));
        }

        Iterator<String> wordIterators = words.iterator();
        while (wordIterators.hasNext()) {
            String target = wordIterators.next();

            if (target.length() != chars.size()) {
                continue;
            }

            if (isCircularWord(chars, target)) {
                wordIterators.remove();
            }
        }
    }

    private static boolean isCircularWord(LinkedList<Character> chars, String word) {
        for (int i = 0; i < chars.size() ; i++) {

            int index = 0;
            boolean isSame = true;
            for (char c : chars) {
                if (word.charAt(index) != c) {
                    isSame = false;
                    break;
                }
                index++;
            }

            if (isSame) {
                return true;
            }

            chars.addLast(chars.removeFirst());
        }

        return false;
    }
}
