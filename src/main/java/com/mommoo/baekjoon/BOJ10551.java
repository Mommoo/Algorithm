package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ10551 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] answers = new int[8];
        String sentence = br.readLine();
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            answers[findFingerNumber(c)] += 1;
        }

        for (int answer: answers) {
            bw.write(String.valueOf(answer));
            bw.newLine();
        }

        bw.close();
        br.close();
    }

    private static int findFingerNumber(char c) {
        switch (c) {
            case 'Q': case 'A': case 'Z': case '1':
                return 0;
            case 'W': case 'S': case 'X': case '2':
                return 1;
            case 'E': case 'D': case 'C': case '3':
                return 2;
            case 'R': case 'F': case 'V': case 'T': case 'G': case 'B': case '4': case '5':
                return 3;
            case 'Y': case 'H': case 'N': case 'U': case 'J': case 'M': case '6': case '7':
                return 4;
            case 'I': case 'K': case ',': case '8':
                return 5;
            case 'O': case 'L': case '.': case '9':
                return 6;
            case '-': case '=': case 'P': case '[': case ']': case ';': case '\'': case '/': case '0':
                return 7;
        }

        return -1;
    }
}
