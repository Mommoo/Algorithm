package com.mommoo.baekjoon.no3985;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cakeLen = Integer.parseInt(reader.readLine());
        int personCount = Integer.parseInt(reader.readLine());
        int[] cakeBuffer = new int[cakeLen + 1];

        PriorityQueue<Person> persons = new PriorityQueue<>();

        int personNumber = 1;
        int expectedMaxTakenCakeNumber = -1;
        int maxCake = -1;
        while (personCount-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());

            if (maxCake < to - from) {
                maxCake = to - from;
                expectedMaxTakenCakeNumber = personNumber;
            }

            int occupiedCake = 0;
            for (int i = from; i <= to; i++) {
                if (cakeBuffer[i] == 0) {
                    occupiedCake++;
                    cakeBuffer[i] = personNumber;
                }
            }

            persons.add(new Person(personNumber, occupiedCake));
            personNumber++;
        }

        reader.close();

        System.out.println(BUILDER.append(expectedMaxTakenCakeNumber)
                                  .append('\n')
                                  .append(persons.poll().number));
    }

    private static class Person implements Comparable<Person> {
        private final int number;
        private final int cake;

        public Person(int number, int cake) {
            this.number = number;
            this.cake = cake;
        }

        @Override
        public int compareTo(Person o) {
            if (o.cake == this.cake) {
                return this.number - o.number;
            }
            return o.cake - this.cake;
        }
    }
}
