package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ5596 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer1 = new StringTokenizer(br.readLine());
        StringTokenizer tokenizer2 = new StringTokenizer(br.readLine());
        int S = 0;
        int T = 0;
        while (tokenizer1.hasMoreTokens()) {
            S += Integer.parseInt(tokenizer1.nextToken());
            T += Integer.parseInt(tokenizer2.nextToken());
        }

        if (S == T) {
            bw.write(String.valueOf(S));
        } else {
            bw.write(String.valueOf(Math.max(S,T)));
        }

        br.close();
        bw.close();
    }
}
