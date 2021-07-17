package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11399 {
    public static void main(String[] args) throws Exception {
        InputData inputData = new InputData();
        LinkedList<Integer> times = new LinkedList<>(inputData.times);
        Collections.sort(times);

        int waitTime = 0;
        int totalTime = 0;

        while (!times.isEmpty()) {
            waitTime += times.pollFirst();
            totalTime += waitTime;
        }

        System.out.println(totalTime);
    }

    private static class InputData {
        private final List<Integer> times;

        private InputData() throws Exception {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                int personCount = Integer.parseInt(bufferedReader.readLine());

                this.times = new ArrayList<>(personCount);
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

                while (tokenizer.hasMoreElements()) {
                    int time = Integer.parseInt(tokenizer.nextToken());
                    this.times.add(time);
                }
            }
        }
    }
}
