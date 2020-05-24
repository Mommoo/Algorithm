package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ10384 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());
        int[] alphabets = new int[26];
        for (int tc = 1; tc <= TC; tc++) {
            Arrays.fill(alphabets, 0);
            String sentence = br.readLine();
            bw.write(String.format("Case %d: ", tc));
            for (int i = 0; i < sentence.length(); i++) {
                char c = Character.toLowerCase(sentence.charAt(i));
                if ('a' <= c && c <= 'z') {
                    alphabets[c - 'a'] += 1;
                }
            }

            int minValue = Integer.MAX_VALUE;
            for (int alphabetCount: alphabets) {
                minValue = Math.min(minValue, alphabetCount);
            }

            if (minValue == 0) {
                bw.write("Not a pangram");
            } else if (minValue == 1) {
                bw.write("Pangram!");
            } else if (minValue == 2) {
                bw.write("Double pangram!!");
            } else {
                bw.write("Triple pangram!!!");
            }

            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
