package com.mommoo.baekjoon.no2231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int BASE_NUMBER;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BASE_NUMBER = Integer.parseInt(reader.readLine());
        int constructorNumber = 0;
        while (constructorNumber++ < BASE_NUMBER) {
            if (isConstructor(constructorNumber)) {
                System.out.println(constructorNumber);
                return;
            }
        }

        System.out.println(0);
    }

    private static boolean isConstructor(int number) {
        String sNumber = String.valueOf(number);
        for (int i = 0 ; i < sNumber.length(); i++) {
            number += sNumber.charAt(i) - '0';
        }

        return number == BASE_NUMBER;
    }
}
