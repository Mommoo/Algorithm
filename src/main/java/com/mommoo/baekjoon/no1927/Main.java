package com.mommoo.baekjoon.no1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();
    private static int[] ARRAY;
    private static int lastIndex = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        ARRAY = new int[N];

        while (N-- > 0) {
            String command = reader.readLine();
            if ("0".equals(command)) {
                BUILDER.append(lastIndex == -1 ? 0 : ARRAY[0])
                       .append('\n');

                if (lastIndex != -1) {
                    removeRootNode();
                }
                continue;
            }

            heapify(Integer.parseInt(command));
        }

        System.out.println(BUILDER);
    }

    public static void heapify(int value) {
        int insertIndex = lastIndex + 1;
        ARRAY[insertIndex] = value;
        int parentIndex = computeParentIndex(insertIndex);
        int currentIndex = insertIndex;
        while (ARRAY[parentIndex] > ARRAY[currentIndex]) {
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;

            if (currentIndex == 0) {
                break;
            }

            parentIndex = computeParentIndex(currentIndex);
        }

        lastIndex++;
    }

    public static void swap(int from, int to) {
        int tmp = ARRAY[from];
        ARRAY[from] = ARRAY[to];
        ARRAY[to] = tmp;
    }

    public static int computeParentIndex(int curIndex) {
        return (curIndex - 1) / 2;
    }

    public static int computeChildLeftIndex(int curIndex) {
        return curIndex * 2 + 1;
    }

    public static void removeRootNode() {
        int lastNodeValue = ARRAY[lastIndex];
        ARRAY[0] = lastNodeValue;
        ARRAY[lastIndex] = 0;
        lastIndex--;

        int curIndex = 0;

        while (true) {
            int childLeftIndex = computeChildLeftIndex(curIndex);
            if (childLeftIndex > lastIndex) {
                break;
            }

            int childRightIndex = childLeftIndex + 1;

            if (childRightIndex > lastIndex && ARRAY[childLeftIndex] < ARRAY[curIndex]) {
                swap(childLeftIndex, curIndex);
                curIndex = childLeftIndex;
                continue;
            }

            if (childRightIndex <= lastIndex) {
                int minIndex = ARRAY[childLeftIndex] > ARRAY[childRightIndex] ? childRightIndex : childLeftIndex;
                if (ARRAY[minIndex] < ARRAY[curIndex]) {
                    swap(minIndex, curIndex);
                    curIndex = minIndex;
                    continue;
                }
            }

            break;
        }
    }
}
