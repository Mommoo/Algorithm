package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2935 {
    private static final String MULTIPLY = "*";

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstNum = reader.readLine();
        String operation = reader.readLine();
        String secondNum = reader.readLine();

        String maxNum = firstNum.length() > secondNum.length() ? firstNum : secondNum;
        String minNum = firstNum.length() <= secondNum.length() ? firstNum : secondNum;

        String resultNum = "";

        if (MULTIPLY.equals(operation)) {
            resultNum = maxNum + minNum.substring(1);
        } else {
            int digit = maxNum.length() - minNum.length();

            if (digit == 0) {
                resultNum = 2 + maxNum.substring(1);
            } else {
                resultNum = maxNum.substring(0, digit) + minNum;
            }
        }

        System.out.println(resultNum);
    }
}
