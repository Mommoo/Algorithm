package com.mommoo.baekjoon.no10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FastMain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        reader.close();

        int count = 0;
        int loadCount = 0;

        for (int i = 0 ; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(' && string.charAt(i + 1) == ')') {
                count += loadCount;
                i++;
                continue;
            }

            if (c == '(') {
                loadCount++;
            } else {
                count++;
                loadCount--;
            }
        }

        System.out.println(count);
    }
}
