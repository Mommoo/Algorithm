package com.mommoo.baekjoon.no10816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] values = createNumbers(reader);
        int[] findNumbers = createNumbers(reader);

        Arrays.sort(values);
        Map<Integer, Integer> numberCounter = createNumberCounter(values);

        StringBuilder builder = new StringBuilder();
        for (int findNumber: findNumbers) {
            builder.append(computeResultByBinarySearch(values, numberCounter, findNumber))
                   .append(" ");
        }
        System.out.println(builder);
    }

    private static int computeResultByBinarySearch(int[] values, Map<Integer, Integer> numberCounter, int number) {
        int beginIndex = 0;
        int endIndex = values.length - 1;
        while (beginIndex <= endIndex) {
            int midIndex = (beginIndex + endIndex) / 2;
            if (values[midIndex] == number) {
                return numberCounter.get(number);
            } else if (values[midIndex] > number) {
                endIndex = midIndex - 1;
            } else {
                beginIndex = midIndex + 1;
            }
        }

        return 0;
    }

    private static int[] createNumbers(BufferedReader reader) throws IOException {
        int numberCount = Integer.parseInt(reader.readLine());
        int[] numbers = new int[numberCount];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0 ; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        return numbers;
    }

    private static Map<Integer, Integer> createNumberCounter(int[] values) {
        Map<Integer, Integer> numberCounters = new HashMap<>();
        for (int value: values) {
            int count = numberCounters.getOrDefault(value, 0);
            numberCounters.put(value, count + 1);
        }
        return numberCounters;
    }
}
