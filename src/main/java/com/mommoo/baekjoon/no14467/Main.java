package com.mommoo.baekjoon.no14467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        int[] cowDirections = new int[count + 1];
        Arrays.fill(cowDirections, -1);

        int moveCounts = 0;

        while (count -- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int cowNumber = Integer.parseInt(tokenizer.nextToken());
            int cowDirection = Integer.parseInt(tokenizer.nextToken());
            if (cowDirections[cowNumber] != cowDirection) {
                if (cowDirections[cowNumber] != -1) {
                    moveCounts++;
                }
                cowDirections[cowNumber] = cowDirection;
            }
        }

        System.out.println(moveCounts);
    }
}
