package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ5218 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        while (N-- > 0) {
            StringTokenizer elements = new StringTokenizer(reader.readLine());
            String element1 = elements.nextToken();
            String element2 = elements.nextToken();

            builder.append("Distances: ")
                   .append(joiningDistances(element1, element2))
                   .append(System.lineSeparator());
        }

        System.out.println(builder);
        reader.close();
    }

    private static String joiningDistances(String element1, String element2) {
        return IntStream.range(0, element1.length())
                        .map(idx -> computeDistance(element1.charAt(idx), element2.charAt(idx)))
                        .boxed()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "));
    }

    private static int computeDistance(char x, char y) {
        return y >= x ? y - x : y + 26 - x;
    }
}
