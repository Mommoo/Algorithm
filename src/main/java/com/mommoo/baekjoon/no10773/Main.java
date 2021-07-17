package com.mommoo.baekjoon.no10773;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> nums = new LinkedList<>();
        while (N -- > 0) {
            Integer num = Integer.parseInt(br.readLine());
            if (num.equals(0)) {
                nums.pop();
                continue;
            }

            nums.push(num);
        }

        br.close();

        int sum = 0;
        while (!nums.isEmpty()) {
            sum += nums.pop();
        }
        System.out.println(sum);
    }
}
