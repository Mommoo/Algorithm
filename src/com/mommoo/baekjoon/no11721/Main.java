package com.mommoo.baekjoon.no11721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        String sentence = br.readLine();
        int beginIndex = 0;
        while (beginIndex + 10 < sentence.length()) {
            builder.append(sentence, beginIndex, beginIndex + 10)
                   .append("\n");
            beginIndex += 10;
        }
        builder.append(sentence.substring(beginIndex))
               .append("\n");

        System.out.println(builder);
    }
}
