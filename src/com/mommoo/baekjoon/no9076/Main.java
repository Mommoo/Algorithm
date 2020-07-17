package com.mommoo.baekjoon.no9076;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();

        while (TC -- > 0) {
            int[] nums = new int[5];
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int index = 0;
            while (tokenizer.hasMoreTokens()) {
                nums[index++] = Integer.parseInt(tokenizer.nextToken());
            }
            Arrays.sort(nums);
            if (nums[3] - nums[1] >= 4) {
                builder.append("KIN");
            } else {
                builder.append(nums[1] + nums[2] + nums[3]);
            }

            builder.append("\n");
        }

        System.out.println(builder);
    }
}
