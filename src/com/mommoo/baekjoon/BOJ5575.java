package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ5575 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0 ; i < 3 ; i ++) {
            bw.write(computeWorkingTimeResult(br.readLine()));
            bw.newLine();
        }

        bw.close();
        br.close();
    }

    private static String computeWorkingTimeResult(String totalWorkingTimeString) {
        StringTokenizer tokenizer = new StringTokenizer(totalWorkingTimeString);
        int beginHour = parseInt(tokenizer);
        int beginMin = parseInt(tokenizer);
        int beginSec = parseInt(tokenizer);
        int endHour = parseInt(tokenizer);
        int endMin = parseInt(tokenizer);
        int endSec = parseInt(tokenizer);

        if (beginSec > endSec) {
            if (endMin == 0) {
                endHour--;
                endMin += 60;
            }

            endMin--;
            endSec += 60;
        }

        if (beginMin > endMin) {
            endHour--;
            endMin += 60;
        }

        int resultHour = endHour - beginHour;
        int resultMin = endMin - beginMin;
        int resultSec = endSec - beginSec;

        return resultHour + " " + resultMin + " " + resultSec;
    }

    private static int parseInt(StringTokenizer tokenizer) {
        return Integer.parseInt(tokenizer.nextToken());
    }
}
