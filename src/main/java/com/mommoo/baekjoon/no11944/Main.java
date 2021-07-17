package com.mommoo.baekjoon.no11944;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        String sNum = tokenizer.nextToken();
        int len = Integer.parseInt(tokenizer.nextToken());

        int sNumLen = sNum.length();
        int num = Integer.parseInt(sNum);

        int count = 1;
        while (count < num) {
            if (sNumLen * count < len) {
                count++;
            } else {
               break;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < count; i++) {
            builder.append(sNum);
        }

        if (sNumLen * count > len) {
            builder.setLength(len);
        }

        System.out.println(builder.toString());
    }
}
