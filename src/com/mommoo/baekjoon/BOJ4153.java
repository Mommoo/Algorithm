package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4153 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        String problem;
        PriorityQueue<Integer> nums = new PriorityQueue<>(Comparator.reverseOrder());
        while (!(problem = br.readLine()).equals("0 0 0")) {
            StringTokenizer tokenizer = new StringTokenizer(problem);
            nums.offer(Integer.parseInt(tokenizer.nextToken()));
            nums.offer(Integer.parseInt(tokenizer.nextToken()));
            nums.offer(Integer.parseInt(tokenizer.nextToken()));

            boolean isAnswer = Math.pow(nums.poll(), 2) == Math.pow(nums.poll(), 2) + Math.pow(nums.poll(), 2);
            builder.append(isAnswer ? "right": "wrong")
                   .append("\n");
        }

        System.out.println(builder);
    }
}
