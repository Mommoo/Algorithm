package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int targetNumber = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        while (true) {
            String stringNumber = br.readLine();
            if ("0".equals(stringNumber)) {
                break;
            }

            int number = Integer.parseInt(stringNumber);
            builder.append(number)
                   .append(" is")
                   .append(number % targetNumber == 0 ? "" : " NOT")
                   .append(" a multiple of ")
                   .append(targetNumber)
                   .append(".\n");
        }

        System.out.println(builder);
    }
}
