package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ1085 {
    public static void main(String[] args) throws Exception {
        List<Integer> locations = inputLocations();
        int x = locations.get(0);
        int y = locations.get(1);
        int w = locations.get(2);
        int h = locations.get(3);

        int xMin = Math.min(x, w - x);
        int yMin = Math.min(y, h - y);
        int min = Math.min(xMin, yMin);

        System.out.println(min);
    }

    private static List<Integer> inputLocations() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] elements = reader.readLine().split(" ");
            return Arrays.stream(elements)
                         .map(Integer::valueOf)
                         .collect(Collectors.toList());
        }
    }
}
