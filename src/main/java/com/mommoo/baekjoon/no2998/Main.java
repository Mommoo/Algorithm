package com.mommoo.baekjoon.no2998;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word = reader.readLine();

        StringBuilder builder = new StringBuilder();
        String newWord = computeFullOctStrings(builder, word);
        builder.setLength(0);

        int needToCount = newWord.length() / 3;
        for (int i = 0; i < needToCount; i++) {
            String octString = newWord.substring(i * 3, i * 3 + 3);
            builder.append(computeBinaryNumber(octString));
        }

        System.out.println(builder);
    }

    private static String computeFullOctStrings(StringBuilder builder, String origin) {
        int needToAppendZeroCount =  3 - (origin.length() % 3);
        if (needToAppendZeroCount == 3) {
            return origin;
        }

        while (needToAppendZeroCount -- > 0) {
            builder.append("0");
        }

        builder.append(origin);

        return builder.toString();
    }

    private static char computeBinaryNumber(String octString) {
        switch (octString) {
            case "000": return '0';
            case "001":	return '1';
            case "010":	return '2';
            case "011":	return '3';
            case "100":	return '4';
            case "101":	return '5';
            case "110":	return '6';
            case "111":	return '7';
            default: return '9';
        }
    }
}
