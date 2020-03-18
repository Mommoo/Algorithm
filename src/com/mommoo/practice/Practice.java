package com.mommoo.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;

public class Practice {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<List<Integer>> results = new ArrayList<>();
        foo(10, 2, 0, values, Collections.emptyList(), results);
        for (List<Integer> result : results) {
            System.out.println("index: " + result);
            System.out.println("sum=" + result.stream().mapToInt(idx -> values[idx]).sum());
        }
    }

    public static void foo(int sum, int count, int nextIndex, int[] values, List<Integer> items, List<List<Integer>> results) {
        if (sum == 0 && count == 0) {
            results.add(items);
            return;
        }

        if (count == 0 || sum < 0) {
            return;
        }

        for (int i = nextIndex; i < values.length; i++) {
            int currentValue = values[i];
            List<Integer> newItems = new ArrayList<>(items);
            newItems.add(i);
            foo(sum - currentValue, count-1, i + 1, values, newItems, results);
        }
    }
}
