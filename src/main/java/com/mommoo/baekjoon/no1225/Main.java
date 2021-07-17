package com.mommoo.baekjoon.no1225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static String NUM1;
    private static String NUM2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        NUM1 = tokenizer.nextToken();
        NUM2 = tokenizer.nextToken();
        reader.close();

        long sum = 0;
        for (int i = 0; i < NUM1.length(); i++) {
            for (int j = 0; j < NUM2.length(); j++) {
                sum += (NUM1.charAt(i) - '0') * (NUM2.charAt(j) - '0');
            }
        }

        System.out.println(sum);
    }
}
