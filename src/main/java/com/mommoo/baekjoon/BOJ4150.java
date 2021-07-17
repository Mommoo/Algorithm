package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ4150 {
    public static void main(String[] args) throws Exception {
        BigInteger endInteger = getNumber();

        if (endInteger.compareTo(BigInteger.valueOf(3)) < 0) {
            System.out.println(1);
            return;
        }

        BigInteger previous = BigInteger.ONE;
        BigInteger previousPrevious = BigInteger.ONE;

        BigInteger current = BigInteger.valueOf(3);


        while (!endInteger.equals(current)) {
            BigInteger next = previous.add(previousPrevious);
            previousPrevious = previous;
            previous = next;
            current = current.add(BigInteger.ONE);
        }

        System.out.println(previous.add(previousPrevious));
    }

    private static BigInteger getNumber() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return new BigInteger(reader.readLine(), 10);
        }
    }
}
