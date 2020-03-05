package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5622 {
    public static void main(String[] args) throws Exception {
        int[] alphabetValues = createAlphabetValues();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word = reader.readLine().toLowerCase();
        reader.close();

        int time = 0;
        for (int idx = 0 ; idx < word.length(); idx++) {
            char alphabet = word.charAt(idx);
            time += alphabetValues[alphabet - 'a'];
        }

        System.out.println(time);
    }

    private static int[] createAlphabetValues() {
        int[] alphabetValues = new int[26];

        fillValue(alphabetValues, 3, 'a', 'b', 'c');
        fillValue(alphabetValues, 4, 'd', 'e', 'f');
        fillValue(alphabetValues, 5, 'g', 'h', 'i');
        fillValue(alphabetValues, 6, 'j', 'k', 'l');
        fillValue(alphabetValues, 7, 'm', 'n', 'o');
        fillValue(alphabetValues, 8, 'p', 'q', 'r', 's');
        fillValue(alphabetValues, 9, 't', 'u', 'v');
        fillValue(alphabetValues, 10, 'w', 'x', 'y', 'z');

        return alphabetValues;
    }

    private static void fillValue(int[] alphabetValues, int value, char... alphabets) {
        for (char alphabet : alphabets) {
            alphabetValues[alphabet - 'a'] = value;
        }
    }
}
