package com.mommoo.practice.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private static final int[] EXAMPLE_DATA = {1, 5, 7, 4, 3, 16, 3, 566, 11, 366, 3, 46737, 33, 116, 155, 53463456, 14, 15, 115, 3, 1656, 5};

    public void sort(int[] data, int beginIndex, int endIndex) {
        int pivotIndex = beginIndex;
        int leftIndex = beginIndex + 1;
        int rightIndex = endIndex;
        int pivotData = data[pivotIndex];
        while (leftIndex < rightIndex) {
            while (data[rightIndex] >= pivotData && leftIndex < rightIndex) { // 각 항목마다 leftIndex 와 rightIndex 를 비교해주는 이유는 배열을 넘어갈 수 있어서.
                rightIndex--;
            }

            while (data[leftIndex] <= pivotData && leftIndex < rightIndex) {
                leftIndex++;
            }

            if (leftIndex < rightIndex) {
                swap(data, leftIndex, rightIndex);
            }
        }

        if (leftIndex == rightIndex && data[leftIndex] < pivotData) {
            swap(data, pivotIndex, leftIndex);
            pivotIndex = leftIndex;
        }

        if (pivotIndex > beginIndex) {
            sort(data, beginIndex, pivotIndex - 1);
        }

        if (endIndex > pivotIndex){
            sort(data, pivotIndex + 1, endIndex);
        }
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private static int[] createRandomArray() {
        Random r = new Random();
        int size = 100;

        int[] randomArray = new int[size];
        for (int i = 0 ; i < size ; i++) {
            randomArray[i] = r.nextInt(1000);
        }

        return randomArray;
    }

    private static boolean isValidSortedArray(int[] array) {
        int[] data = Arrays.copyOf(array, array.length);
        Arrays.sort(data);
        return Arrays.equals(data, array);
    }

    public static void main(String[] args) {
        int count = 10;
        while (count-- > 0) {
            int[] data = createRandomArray();
            new QuickSort().sort(data, 0, data.length - 1);
            System.out.println(isValidSortedArray(data) +" : "+ Arrays.toString(data));
        }
    }
}
