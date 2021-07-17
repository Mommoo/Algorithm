package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ5612 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int time = Integer.parseInt(br.readLine());
        int initCarCount = Integer.parseInt(br.readLine());
        int answer = 0;
        while (time -- > 0) {
            StringTokenizer carInOut = new StringTokenizer(br.readLine());
            int carInCount = Integer.parseInt(carInOut.nextToken());
            int carOutCount = Integer.parseInt(carInOut.nextToken());
            int remainCarCount = carInCount + initCarCount - carOutCount;

            if (remainCarCount < 0) {
                answer = 0;
                break;
            }

            answer = Math.max(answer, remainCarCount);
            initCarCount = remainCarCount;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();

        bw.close();
        br.close();
    }
}
