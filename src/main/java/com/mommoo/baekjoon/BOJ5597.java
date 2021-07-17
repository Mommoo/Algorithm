package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ5597 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> students = createStudents();
        for (int i = 0; i < 28; i++) {
            students.remove(reader.readLine());
        }

        System.out.println(students.stream().sorted(Comparator.comparingInt(Integer::parseInt)).collect(Collectors.joining("\n")));
    }

    private static Set<String> createStudents() {
        return IntStream.rangeClosed(1, 30)
                        .boxed()
                        .map(String::valueOf)
                        .collect(Collectors.toSet());
    }
}
