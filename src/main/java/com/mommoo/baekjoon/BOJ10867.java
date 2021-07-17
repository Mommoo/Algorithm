package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10867 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.readLine();

        boolean[] numberVisits = new boolean[2001];

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        while (tokenizer.hasMoreTokens()) {
            int numberIndex = Integer.parseInt(tokenizer.nextToken()) + 1000;
            numberVisits[numberIndex] = true;
        }

        int index = 0;
        for (boolean numberVisit: numberVisits) {
            if (numberVisit) {
                bw.write(String.valueOf(index - 1000));
                bw.write(" ");
            }

            index++;
        }

        bw.close();
        br.close();
    }
}
