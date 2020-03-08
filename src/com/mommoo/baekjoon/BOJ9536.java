package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ9536 {
    private static final String QUESTION = "what does the fox say?";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringBuilder builder = new StringBuilder();
        while (N -- > 0) {
            String stringRoars = reader.readLine();
            Set<String> excludeRoars = buildRoars(reader);
            String joiningFoxRoars = joiningFoxRoars(stringRoars, excludeRoars);

            builder.append(joiningFoxRoars)
                   .append(System.lineSeparator());
        }

        System.out.println(builder);
        reader.close();
    }

    private static Set<String> buildRoars(BufferedReader reader) throws Exception {
        Set<String> roars = new HashSet<>();
        while (true) {
            String nextLine = reader.readLine();
            if (nextLine.equals(QUESTION)) {
                break;
            }
            roars.add(parseRoar(nextLine));
        }

        return roars;
    }

    private static String parseRoar(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        tokenizer.nextToken();
        tokenizer.nextToken();
        return tokenizer.nextToken();
    }

    private static String joiningFoxRoars(String stringRoars, Set<String> excludeRoars) {
        List<String> foxRoars = new ArrayList<>();

        StringTokenizer roars = new StringTokenizer(stringRoars);
        while (roars.hasMoreTokens()) {
            String roar = roars.nextToken();
            if (excludeRoars.contains(roar)) {
                continue;
            }

            foxRoars.add(roar);
        }

        return String.join(" ", foxRoars);
    }
}
