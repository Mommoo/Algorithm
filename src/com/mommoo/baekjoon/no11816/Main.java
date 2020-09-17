package com.mommoo.baekjoon.no11816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String number = getNumber();
        if (isHexadecimal(number)) {
            System.out.println(Integer.parseInt(number.substring(2), 16));
        } else if (isOctal(number)) {
            System.out.println(Integer.parseInt(number, 8));
        } else {
            System.out.println(number);
        }
    }

    private static String getNumber() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String number = reader.readLine();
        reader.close();

        return number;
    }

    private static boolean isHexadecimal(String number) {
        return number.charAt(1) == 'x';
    }

    private static boolean isOctal(String number) {
        return number.charAt(0) == '0' && !isHexadecimal(number);
    }
}
