package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16171 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String find = br.readLine();

        String originWithNumberRemoved = removeNumber(origin);

        System.out.println(originWithNumberRemoved.contains(find) ? 1 : 0);
    }

    private static String removeNumber(String origin) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < origin.length(); i++) {
            char c = origin.charAt(i);
            if ('0' <= c && c <= '9') {
                continue;
            }
            builder.append(c);
        }

        return builder.toString();
    }
}
