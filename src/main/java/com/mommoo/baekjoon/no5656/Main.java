package com.mommoo.baekjoon.no5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int order = 1;
        while (true) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int num1 = Integer.parseInt(tokenizer.nextToken());
            String operator = tokenizer.nextToken();
            int num2 = Integer.parseInt(tokenizer.nextToken());

            if ("E".equals(operator)) {
                break;
            }

            boolean result = false;
            if (">".equals(operator)) {
                result = num1 > num2;
            } else if ("<".equals(operator)) {
                result = num1 < num2;
            } else if (">=".equals(operator)) {
                result = num1 >= num2;
            } else if ("<=".equals(operator)) {
                result = num1 <= num2;
            } else if ("==".equals(operator)) {
                result = num1 == num2;
            } else if ("!=".equals(operator)) {
                result = num1 != num2;
            }

            BUILDER.append("Case ")
                   .append(order)
                   .append(": ")
                   .append(result)
                   .append("\n");

            order++;
        }

        reader.close();
        System.out.println(BUILDER);
    }
}
