package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2864 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        List<String> elements = Arrays.asList(tokenizer.nextToken(), tokenizer.nextToken());

        int minimumSum = elements.stream()
                .map(element -> element.replaceAll("6", "5"))
                .mapToInt(Integer::parseInt)
                .sum();

        int maximumSum = elements.stream()
                                 .map(element -> element.replaceAll("5", "6"))
                                 .mapToInt(Integer::parseInt)
                                 .sum();

        System.out.println(minimumSum + " " + maximumSum);
        reader.close();
    }
}
