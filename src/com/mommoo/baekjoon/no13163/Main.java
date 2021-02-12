package com.mommoo.baekjoon.no13163;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();
    private static final String GOD = "god";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(reader.readLine());
        while (TC-- > 0) {
            BUILDER.setLength(0);
            BUILDER.append(GOD);
            String words = reader.readLine();
            int beginIndex = words.indexOf(' ') + 1;
            while (true) {
                int nextIndex = words.indexOf(' ', beginIndex);
                if (nextIndex == -1) {
                    BUILDER.append(words, beginIndex, words.length());
                    break;
                }
                BUILDER.append(words, beginIndex, nextIndex);
                beginIndex = nextIndex + 1;
            }
            writer.write(BUILDER.toString());
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}
