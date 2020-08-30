package com.mommoo.baekjoon.no1032;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String[] FILE_NAMES;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        FILE_NAMES = createFilenames(reader, count);
        System.out.println(computePatterns());
    }

    private static String[] createFilenames(BufferedReader reader, int count) throws IOException {
        String[] filenames = new String[count];

        for (int i = 0 ; i < count; i++) {
            filenames[i] = reader.readLine();
        }

        return filenames;
    }

    private static String computePatterns() {
        StringBuilder builder = new StringBuilder();
        int baseLen = FILE_NAMES[0].length();
        for (int i = 0; i < baseLen; i++) {
            builder.append(computePattern(i));
        }
        return builder.toString();
    }

    private static char computePattern(int index) {
        char baseChar = FILE_NAMES[0].charAt(index);

        if (FILE_NAMES.length == 1) {
            return baseChar;
        }

        for (String filename: FILE_NAMES) {
            if (filename.charAt(index) != baseChar) {
                return '?';
            }
        }

        return baseChar;
    }
}
