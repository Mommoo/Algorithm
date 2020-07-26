package com.mommoo.no1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FastMain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long[] valueNumbers = createNumbers(reader);
        long[] findNumbers = createNumbers(reader);

        Arrays.sort(valueNumbers);
        StringBuilder builder = new StringBuilder();
        for (long findNumber: findNumbers) {
            builder.append(computeValueByFindBinarySearch(valueNumbers, findNumber))
                   .append("\n");
        }
        System.out.println(builder);
    }

    private static int computeValueByFindBinarySearch(long[] numbers, long value) {
        int beginIndex = 0;
        int endIndex = numbers.length - 1;
        while (beginIndex <= endIndex) {
            int midIndex = (beginIndex + endIndex) / 2;
            if (numbers[midIndex] > value) {
                endIndex = midIndex - 1;
            } else if (numbers[midIndex] < value) {
                beginIndex = midIndex + 1;
            } else {
                return 1;
            }
        }

        return 0;
    }

    private static long[] createNumbers(BufferedReader reader) throws IOException {
        int size = Integer.parseInt(reader.readLine());
        long[] values = new long[size];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            values[i] = Long.parseLong(tokenizer.nextToken());
        }

        return values;
    }
}
