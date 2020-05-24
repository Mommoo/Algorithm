package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(br.readLine());
        int result = 0;
        String numStrings = br.readLine();
        for (int i = 0; i < len; i++) {
            result += numStrings.charAt(i) - '0';
        }

        bw.write(String.valueOf(result));

        bw.close();
        br.close();
    }
}
