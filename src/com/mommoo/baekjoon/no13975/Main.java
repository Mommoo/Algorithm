package com.mommoo.baekjoon.no13975;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();

        while (TC -- > 0) {
            int count = Integer.parseInt(br.readLine());
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            while (count -- > 0) {
                priorityQueue.offer(Long.parseLong(tokenizer.nextToken()));
            }

            long totalCost = 0;
            while (true) {
                long combineFileSize = priorityQueue.poll() + priorityQueue.poll();
                totalCost += combineFileSize;

                if (priorityQueue.isEmpty()) {
                    break;
                }

                priorityQueue.offer(combineFileSize);
            }

            appendResult(totalCost);
        }

        System.out.println(BUILDER);
    }

    private static void appendResult(long result) {
        BUILDER.append(result)
               .append("\n");
    }
}
