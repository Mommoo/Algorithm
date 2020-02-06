package com.mommoo.practice;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PermutationAndCombination {
    public static void main(String[] args) {
        System.out.println(getPermutation(5, 1));
        System.out.println(getPermutation(5, 2));
        System.out.println(getPermutation(5, 3));
        System.out.println(getPermutation(5, 4));
        System.out.println("------------------------------------");
        System.out.println(getCombination(5, 1));
        System.out.println(getCombination(5, 2));
        System.out.println(getCombination(5, 3));
        System.out.println(getCombination(5, 4));
    }

    private static List<String> getPermutation(int number, int count) {
        return doPermutation(number, count, "").stream()
                                               .map(String::trim)
                                               .collect(Collectors.toList());
    }

    private static List<String> getCombination(int number, int count) {
        return doCombination(1, number, count, "").stream()
                                                  .map(String::trim)
                                                  .collect(Collectors.toList());
    }

    private static List<String> doPermutation(int remainNumber, int remainCount, String result) {
        if (remainCount == 0 && remainNumber == 0) {
            return Collections.singletonList(result);
        } else if (remainCount < 0 || remainNumber < 0) {
            return Collections.emptyList();
        }

        remainCount--;

        List<String> results = new LinkedList<>();

        for (int number = 1; number <= remainNumber; number++) {
            int nextNumber = remainNumber - number;
            String nextResult = result + " " + number;
            results.addAll(doPermutation(nextNumber, remainCount, nextResult));
        }

        return results;
    }

    private static List<String> doCombination(int beginNumber, int remainNumber, int remainCount, String result) {
        if (remainCount == 0 && remainNumber == 0) {
            return Collections.singletonList(result);
        } else if (remainCount < 0 || remainNumber < 0) {
            return Collections.emptyList();
        }

        remainCount--;

        List<String> results = new LinkedList<>();

        for (int number = beginNumber; number <= remainNumber; number++) {
            int nextNumber = remainNumber - number;
            String nextResult = result + " " + number;
            results.addAll(doCombination(number, nextNumber, remainCount, nextResult));
        }

        return results;
    }
}
