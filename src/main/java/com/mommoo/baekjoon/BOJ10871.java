package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ10871 {
    public static void main(String[] args) throws Exception {
        InputData inputData = new InputData();
        System.out.println(inputData.numbers
                                    .stream()
                                    .filter(number -> number < inputData.basicNumber)
                                    .map(String::valueOf)
                                    .collect(Collectors.joining(" ")));
    }

    private static class InputData {
        private final int basicNumber;
        private final List<Integer> numbers;

        public InputData() throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            this.basicNumber = Integer.parseInt(reader.readLine().split(" ")[1]);
            this.numbers = Arrays.stream(reader.readLine().split(" "))
                                 .map(Integer::parseInt)
                                 .collect(Collectors.toList());
            reader.close();
        }
    }
}
