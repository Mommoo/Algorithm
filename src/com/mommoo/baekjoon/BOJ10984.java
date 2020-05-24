package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10984 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());
        while (TC -- > 0) {
            int subjectCount = Integer.parseInt(br.readLine());
            int totalCredit = 0;
            double totalCreditScore = 0;
            while (subjectCount -- > 0) {
                StringTokenizer tokenizer = new StringTokenizer(br.readLine());
                int credit = Integer.parseInt(tokenizer.nextToken());
                totalCredit += credit;
                totalCreditScore += (double)credit * Double.parseDouble(tokenizer.nextToken());
            }

            bw.write(String.valueOf(totalCredit));
            bw.write(" ");
            bw.write(String.format("%.1f", totalCreditScore / (double)totalCredit));
            bw.newLine();
        }

        bw.close();
        br.close();
    }
}
