package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ2309 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = 9;
        List<Integer> heights = new ArrayList<>();
        while (N -- > 0) {
            heights.add(Integer.parseInt(reader.readLine()));
        }

        int totalSum = heights.stream().mapToInt(Integer::intValue).sum();
        List<Integer> indexes = findNotDwarfIndexes(totalSum, heights, 0, Collections.emptyList());

        heights.remove(indexes.get(1).intValue());
        heights.remove(indexes.get(0).intValue());

        System.out.println(heights.stream()
                                  .mapToInt(Integer::intValue)
                                  .sorted()
                                  .mapToObj(String::valueOf)
                                  .collect(Collectors.joining(System.lineSeparator())));
    }

    private static List<Integer> findNotDwarfIndexes(int remainSum, List<Integer> heights, int nextIndex, List<Integer> indexes) {
        if (remainSum == 100 && indexes.size() == 2) {
            return indexes;
        }

        if (indexes.size() == 2 && remainSum != 100) {
            return Collections.emptyList();
        }

        for (int i = nextIndex; i < heights.size() ; i++) {
            List<Integer> newIndexes = new ArrayList<>(indexes);
            newIndexes.add(i);
            List<Integer> result = findNotDwarfIndexes(remainSum - heights.get(i), heights, nextIndex+1, newIndexes);
            if (!result.isEmpty()) {
                return result;
            }
        }

        return Collections.emptyList();
    }
}
