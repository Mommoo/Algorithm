package com.mommoo.baekjoon.no10818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        while (tokenizer.hasMoreTokens()) {
            Integer number = Integer.parseInt(tokenizer.nextToken());
            minQueue.add(number);
            maxQueue.add(number);
        }

        System.out.println(minQueue.poll() + " " + maxQueue.poll());
    }
}
