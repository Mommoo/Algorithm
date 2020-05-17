package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ5554 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int totalSecond = Integer.parseInt(br.readLine());
        totalSecond += Integer.parseInt(br.readLine());
        totalSecond += Integer.parseInt(br.readLine());
        totalSecond += Integer.parseInt(br.readLine());

        bw.write(String.valueOf(totalSecond / 60));
        bw.newLine();
        bw.write(String.valueOf(totalSecond - 60 * (totalSecond / 60)));

        bw.close();
        br.close();
    }
}
