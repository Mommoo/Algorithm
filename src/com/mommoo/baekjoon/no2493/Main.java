package com.mommoo.baekjoon.no2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        Stack<Tower> towers = new Stack<>();
        int index = 1;
        while (tokenizer.hasMoreTokens()) {
            int height = Integer.parseInt(tokenizer.nextToken());

            while (!towers.isEmpty() && towers.peek().height < height) {
                towers.pop();
            }

            if (towers.isEmpty()) {
                BUILDER.append("0 ");
            } else {
                BUILDER.append(towers.peek().index).append(" ");
            }

            towers.push(new Tower(height, index));

            index++;
        }

        System.out.println(BUILDER);
    }

    private static class Tower {
        private final int height;
        private final int index;

        public Tower(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }
}
