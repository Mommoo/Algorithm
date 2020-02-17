package com.mommoo.practice.sort;

import java.util.Arrays;
import java.util.Random;

public class SortTester {
    private final int[] testArray;

    public SortTester(int size) {
        this.testArray = createRandomArray(size);
    }

    public boolean isRightSorted(int[] array, boolean ascending) {
        int[] sortedArray = Arrays.copyOf(testArray, testArray.length);
        Arrays.sort(sortedArray);
        if (!ascending) {
            reverse(sortedArray);
        }
        return Arrays.equals(sortedArray, array);
    }

    public int[] getSample() {
        return Arrays.copyOf(testArray, testArray.length);
    }

    private static void reverse(int[] data) {
        for (int left = 0, right = data.length - 1; left < right; left++, right--) {
            // swap the values at the left and right indices
            int temp = data[left];
            data[left]  = data[right];
            data[right] = temp;
        }
    }

    private static int[] createRandomArray(int size) {
        Random r = new Random();

        int[] randomArray = new int[size];
        for (int i = 0 ; i < size ; i++) {
            randomArray[i] = r.nextInt(1000);
        }

        return randomArray;
    }
}
