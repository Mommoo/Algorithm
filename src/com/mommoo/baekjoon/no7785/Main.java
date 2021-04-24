package com.mommoo.baekjoon.no7785;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static int N;
    private static final String ENTER = "enter";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());

        Set<String> enters = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String string = reader.readLine();
            int splitIndex = string.indexOf(' ');
            String name = string.substring(0, splitIndex);
            String action = string.substring(splitIndex + 1);

            if (ENTER.equals(action)) {
                enters.add(name);
            } else {
                enters.remove(name);
            }
        }

        List<String> names = new ArrayList<>(enters);
        names.sort(Comparator.reverseOrder());

        StringBuilder builder = new StringBuilder();
        for (String name : names) {
            builder.append(name).append('\n');
        }

        System.out.println(builder);
    }
}
