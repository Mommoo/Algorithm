package com.mommoo.baekjoon.no10773;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FastMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int pointer = 0;
        while (N -- > 0) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                nums[--pointer] = 0;
                continue;
            }

            nums[pointer++] = num;
        }

        br.close();

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println(sum);
    }
}
