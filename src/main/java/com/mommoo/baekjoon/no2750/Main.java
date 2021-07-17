package com.mommoo.baekjoon.no2750;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        int[] numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }
        sort(numbers);
        StringBuilder builder = new StringBuilder();
        for (int number: numbers) {
            builder.append(number).append("\n");
        }

        System.out.println(builder);
    }

    public static void sort(int[] numbers) {
        if (numbers.length == 1) {
            return;
        }
        quickSort(numbers, 0, numbers.length - 1);
    }

    private static void quickSort(int[] numbers, int beginIndex, int endIndex) {
        if (beginIndex >= endIndex) {
            return;
        }

        int leftIndex = beginIndex;
        int rightIndex = endIndex;
        int pivotValue = numbers[leftIndex++];

        while (leftIndex < rightIndex) {
            while (leftIndex < rightIndex && numbers[rightIndex] >= pivotValue) {
                rightIndex--;
            }

            while (leftIndex < rightIndex && numbers[leftIndex] <= pivotValue) {
                leftIndex++;
            }

            swap(numbers, leftIndex, rightIndex);
        }

        if (numbers[leftIndex] >= pivotValue) {
            leftIndex--;
        }

        int pivotIndex = leftIndex;

        swap(numbers, beginIndex, pivotIndex);

        quickSort(numbers, beginIndex, pivotIndex -1);
        quickSort(numbers, pivotIndex + 1, endIndex);
    }

    private static void swap(int[] numbers, int index1, int index2) {
        int tmp = numbers[index1];
        numbers[index1] = numbers[index2];
        numbers[index2] = tmp;
    }
}
