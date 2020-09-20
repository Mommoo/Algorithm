package com.mommoo.baekjoon.no14425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static Set<String> BASE_STRINGS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int baseStringCount = Integer.parseInt(tokenizer.nextToken());
        int findStringCount = Integer.parseInt(tokenizer.nextToken());

        BASE_STRINGS = createBaseStrings(baseStringCount, reader);

        int containCount = 0;
        while (findStringCount-- > 0) {
            String findString = reader.readLine();
            containCount += BASE_STRINGS.contains(findString) ? 1 : 0;
        }

        System.out.println(containCount);
    }

    private static Set<String> createBaseStrings(int count, BufferedReader reader) throws IOException {
        Set<String> baseStrings = new HashSet<>(count);

        for (int i = 0; i < count; i++) {
            baseStrings.add(reader.readLine());
        }

        return baseStrings;
    }
}
