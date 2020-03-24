package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BOJ10814 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        IntStream.range(0, N)
                 .mapToObj(idx -> PersonInfo.create(idx, readLine(reader)))
                 .sorted()
                 .map(p -> String.format("%d %s", p.age, p.name))
                 .forEach(System.out::println);
    }

    private static String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException io) {
            return null;
        }
    }

    private static class PersonInfo implements Comparable<PersonInfo> {
        private final int order;
        private final int age;
        private final String name;

        public static PersonInfo create(int order, String profile) {
            StringTokenizer tokenizer = new StringTokenizer(profile);
            return new PersonInfo(order, Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken());
        }

        public PersonInfo(int order, int age, String name) {
            this.order = order;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(PersonInfo o) {
            if (this.age == o.age) {
                return this.order - o.order;
            }
            return this.age - o.age;
        }
    }
}
