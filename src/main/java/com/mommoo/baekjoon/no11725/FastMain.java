package com.mommoo.baekjoon.no11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FastMain {
    private static final Integer ROOT_NODE = 1;
    private static boolean[] visits;
    private static List<Integer>[] nodes;
    private static int[] rootNodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        visits = new boolean[size + 1];
        rootNodes = new int[size + 1];

        nodes = new ArrayList[size + 1];
        for (int i = 0 ; i < size + 1; i ++) {
            nodes[i] = new ArrayList<>();
        }

        size--;
        while (size -- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            Integer node1 = Integer.parseInt(tokenizer.nextToken());
            Integer node2 = Integer.parseInt(tokenizer.nextToken());

            nodes[node1].add(node2);
            nodes[node2].add(node1);
        }

        dfs(ROOT_NODE);

        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < rootNodes.length; i++) {
            builder.append(rootNodes[i])
                   .append("\n");
        }
        System.out.println(builder);
    }

    public static void dfs(int node) {
        for (int nextNode: nodes[node]) {
            if (visits[nextNode]) {
                continue;
            }

            rootNodes[nextNode] = node;
            visits[nextNode] = true;
            dfs(nextNode);
        }
    }
}
