package com.mommoo.baekjoon.no10820;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static final int LOWER_CASE = 0;
    private static final int UPPER_CASE = 1;
    private static final int NUMBER = 2;
    private static final int BLANK = 3;

    private static final int[] COUNTER = new int[4];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = "";
        while ((line = br.readLine()) != null) {
            count(line);
            write(bw);
            clear();
        }

        br.close();
        bw.close();
    }

    private static void count(String string) {
        for (int i = 0 ; i < string.length() ; i++) {
            char c = string.charAt(i);
            if ('a' <= c && c <= 'z') {
                COUNTER[LOWER_CASE]++;
            } else if ('A' <= c && c <= 'Z') {
                COUNTER[UPPER_CASE]++;
            } else if ('0' <= c && c <= '9') {
                COUNTER[NUMBER]++;
            } else if (' ' == c) {
                COUNTER[BLANK]++;
            }
        }
    }

    private static void clear() {
        COUNTER[LOWER_CASE] = COUNTER[UPPER_CASE] = COUNTER[NUMBER] = COUNTER[BLANK] = 0;
    }

    private static void write(BufferedWriter bw) throws Exception {
        bw.write(COUNTER[LOWER_CASE] + " " + COUNTER[UPPER_CASE] + " " + COUNTER[NUMBER] + " " + COUNTER[BLANK]);
        bw.newLine();
    }
}
