package com.mommoo.baekjoon.no8949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        String num1 = tokenizer.nextToken();
        String num2 = tokenizer.nextToken();

        StringBuilder builder = new StringBuilder();
        int len = Math.min(num1.length(), num2.length());

        if (num1.length() > num2.length()) {
            builder.append(num1, 0, num1.length() - len);
            num1 = num1.substring(num1.length() - len);
        } else {
            builder.append(num2, 0, num2.length() - len);
            num2 = num2.substring(num2.length() - len);
        }

        for (int i = 0; i < num1.length(); i++) {
            int num1Value = num1.charAt(i) - '0';
            int num2Value = num2.charAt(i) - '0';

            int numSum = num1Value + num2Value;
            builder.append(numSum);
        }

        System.out.println(builder);
    }
}
