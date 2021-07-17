package com.mommoo.practice.sort;

import java.util.Arrays;

public class HeapSort {
    private final int[] data;
    private int heapSize;

    public HeapSort(int[] data) {
        this.data = data;
    }

    private void heapify(boolean max) {
        heapSize = data.length;
        for (int i = data.length / 2 ; i >=0 ; i--) {
            heapify(i, max);
            System.out.println(Arrays.toString(data));
        }

        System.out.println("------------------------------");

        while (heapSize-- > 0) {
            swap(0, heapSize);
            heapify(0, max);
            System.out.println(Arrays.toString(data));
        }
    }

    private int getChildValueIndex(int index, boolean max) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if (right < heapSize) {
            boolean isRightBiggerThanLeft = data[right] >= data[left];
            return max == isRightBiggerThanLeft ? right : left;
        } else if (left < heapSize) {
            return left;
        }

        return -1;
    }

    private void heapify(int index, boolean max) {
        int maxValueIndex = getChildValueIndex(index, max);

        if (maxValueIndex != -1 && (max == data[index] < data[maxValueIndex])) {
            swap(index, maxValueIndex);
            heapify(maxValueIndex, max);
        }
    }

    private void swap(int from, int to) {
        int temp = data[from];
        data[from] = data[to];
        data[to] = temp;
    }

    public static void main(String[] args) {
        SortTester sortTester = new SortTester(100);
        HeapSort heapSort = new HeapSort(sortTester.getSample());
        heapSort.heapify(true);
        System.out.println(sortTester.isRightSorted(heapSort.data, true));
        heapSort.heapify(false);
        System.out.println(sortTester.isRightSorted(heapSort.data, false));
    }
}
