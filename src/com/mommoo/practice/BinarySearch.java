package com.mommoo.practice;

public class BinarySearch {
    private static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    public static void main(String[] args) {
        int beginIndex = 0;
        int endIndex = array.length - 1;

        int findNum = 20;
        while (beginIndex <= endIndex) {
            int middleIndex = ( endIndex + beginIndex ) / 2;
            System.out.println(String.format("%d %d %d", beginIndex, middleIndex, endIndex));
            if (array[middleIndex] == findNum) {
                System.out.println("index : " + middleIndex);
                break;
            }

            if (array[middleIndex] > findNum) {
                endIndex = middleIndex - 1;
            } else {
                beginIndex = middleIndex + 1;
            }
        }

        System.out.println("end");
    }
}
