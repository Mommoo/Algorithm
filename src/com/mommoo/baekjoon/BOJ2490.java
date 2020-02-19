package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ2490 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 3; i++) {
            int count = reader.readLine()
                              .chars()
                              .filter(c -> c != (int)' ')
                              .map(cInt -> cInt - '0')
                              .sum();

            writer.write(convertToYut(count) + "\n");
        }

        writer.flush();
    }

    private static char convertToYut(int count) {
        switch (count) {
            case 0: return 'D';
            case 1: return 'C';
            case 2: return 'B';
            case 3: return 'A';
            case 4: return 'E';
        }

        return ' ';
    }
}
