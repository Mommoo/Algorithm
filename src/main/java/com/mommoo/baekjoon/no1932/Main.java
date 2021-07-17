package com.mommoo.baekjoon.no1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int MAX_LEVEL;
    private static int[][] NODES;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MAX_LEVEL = Integer.parseInt(reader.readLine());
        NODES = createNodes(reader);

        for (int level = MAX_LEVEL - 1; level > 0; level--) {
            int[] nodeLine = NODES[level];
            for (int i = 1; i <= level; i++) {
                NODES[level - 1][i - 1] += Math.max(nodeLine[i], nodeLine[i-1]);
            }
        }

        System.out.println(NODES[0][0]);
    }

    private static int[][] createNodes(BufferedReader reader) throws IOException {
        int[][] nodes = new int[MAX_LEVEL][];
        for (int i = 0 ; i < MAX_LEVEL; i++) {
            int len = i + 1;
            nodes[i] = new int[len];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int index = 0;
            while (tokenizer.hasMoreTokens()) {
                nodes[i][index++] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        return nodes;
    }
}
