package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11365 {
    private static final String END = "END";
    private static final String NEW_LINE = "\n";
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String sentence = reader.readLine();
            if (END.equals(sentence)) {
                break;
            }

            for (int i = sentence.length() - 1 ; i >= 0; i--) {
                BUILDER.append(sentence.charAt(i));
            }

            BUILDER.append(NEW_LINE);
        }

        System.out.println(BUILDER);
    }
}
