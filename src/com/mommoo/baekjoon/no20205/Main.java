package com.mommoo.baekjoon.no20205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder TEMP_BUILDER = new StringBuilder();
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int size = Integer.parseInt(tokenizer.nextToken());
        int ratio = Integer.parseInt(tokenizer.nextToken());

        while (size-- > 0) {
            ratioWrite(reader.readLine(), ratio);
        }

        reader.close();
        System.out.println(BUILDER);
    }

    public static void ratioWrite(String line, int ratio) {
        String createdLine = createRatioLine(line, ratio);
        for (int i = 0; i < ratio; i++) {
            BUILDER.append(createdLine).append('\n');
        }
    }

    public static String createRatioLine(String line, int ratio) {
        TEMP_BUILDER.setLength(0);
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            String value = tokenizer.nextToken();
            for (int i = 0 ; i < ratio; i++) {
                TEMP_BUILDER.append(" ").append(value);
            }
        }

        return TEMP_BUILDER.substring(1);
    }
}
