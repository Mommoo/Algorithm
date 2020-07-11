package com.mommoo.baekjoon.no1717;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class FasterMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int[] parents =  createParents(N);

        while (M -- > 0) {
            tokenizer = new StringTokenizer(br.readLine());
            String operation = tokenizer.nextToken();
            int number1 = Integer.parseInt(tokenizer.nextToken());
            int number2 = Integer.parseInt(tokenizer.nextToken());

            if ("1".equals(operation)) {
                bw.write(computeIsNumberJoinResult(parents, number1, number2));
                bw.newLine();
            } else {
                joinNumbers(parents, number1, number2);
            }
        }

        br.close();
        bw.close();
    }

    private static int[] createParents(int N) {
        int[] parents =  new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        return parents;
    }

    private static String computeIsNumberJoinResult(int[] parents, int number1, int number2) {
        return findParent(parents, number1) == findParent(parents, number2) ? "YES" : "NO";
    }

    private static int findParent(int[] parents, int number) {
        if (parents[number] == number) {
            return number;
        }

        return parents[number] = findParent(parents, parents[number]);
    }

    private static void joinNumbers(int[] parents, int number1, int number2) {
        int parent1 = findParent(parents, number1);
        int parent2 = findParent(parents, number2);
        parents[parent1] = parent2;
    }
}
