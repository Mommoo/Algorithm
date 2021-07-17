package com.mommoo.baekjoon.no2866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    private static int R;
    private static int C;
    private static char[][] TABLE;

    private static Set<String> WORDS = new HashSet<>();

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        setUp();

        computeMaxCount(1, R - 1, R - 1);

        System.out.println(count);
    }

    private static void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());


        TABLE = new char[R][C];
        for (int row = 0 ; row < R; row++) {
            String word = reader.readLine();
            for (int col = 0; col < C; col++) {
                TABLE[row][col] = word.charAt(col);
            }
        }

        reader.close();
    }

    private static boolean containDuplicateWords(int beginRow, int endRow) {
        WORDS.clear();
        for (int col = 0 ; col < C; col++) {
            String word = parseRowWord(beginRow, endRow, col);

            if (WORDS.contains(word)) {
                return true;
            }

            WORDS.add(word);
        }

        return false;
    }

    private static String parseRowWord(int beginRow, int endRow, int col) {
        BUILDER.setLength(0);
        for (int row = beginRow ; row <= endRow; row++) {
            BUILDER.append(TABLE[row][col]);
        }

        return BUILDER.toString();
    }

    private static void computeMaxCount(int left, int right, int end) {
        if (left > right) return ;

        int middle = (left + right) / 2;

        if (containDuplicateWords(middle, end)) {
            computeMaxCount(left, middle -1, end);
        } else {
            if (count < middle) {
                count = middle;
            }

            computeMaxCount(middle + 1, right, end);
        }
    }
}
