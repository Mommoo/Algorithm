package com.mommoo.baekjoon.no1357;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] twoNumbers = getTwoNumbers();
        System.out.println(rev(Integer.toString(rev(twoNumbers[0]) + rev(twoNumbers[1]))));
    }

    private static String[] getTwoNumbers() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        reader.close();
        return new String[]{tokenizer.nextToken(), tokenizer.nextToken()};
    }

    private static int rev(String number) {
        int beginIndex = number.length() - 1;
        while (number.charAt(beginIndex) == 0) {
            beginIndex--;
        }
        return Integer.parseInt(new StringBuilder().append(number, 0, beginIndex+1).reverse().toString());
    }
}
