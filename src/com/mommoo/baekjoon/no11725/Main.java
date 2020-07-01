package com.mommoo.baekjoon.no11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final Integer ROOT_NODE = 1;
    private static Map<Integer, List<Integer>> nodes = new HashMap<>();
    private static boolean[] visits;
    private static int[] rootNodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        visits = new boolean[size + 1];
        rootNodes = new int[size + 1];

        size--;
        while (size -- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            Integer node1 = Integer.parseInt(tokenizer.nextToken());
            Integer node2 = Integer.parseInt(tokenizer.nextToken());

            putNode(node1, node2);
            putNode(node2, node1);
        }

        // 무한 루프 처리가 덜 된듯.
        Queue<Integer> nodeQueue = new LinkedList<>();
        nodeQueue.offer(ROOT_NODE);
        while (!nodeQueue.isEmpty()) {
            Integer node = nodeQueue.poll();
            List<Integer> nextNodes = nodes.get(node);
            for (Integer nextNode: nextNodes) {
                if (visits[nextNode]) {
                    continue;
                }

                rootNodes[nextNode] = node;
                visits[nextNode] = true;
                nodeQueue.offer(nextNode);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < rootNodes.length; i++) {
            builder.append(rootNodes[i])
                   .append("\n");
        }
        System.out.println(builder);
    }

    private static void putNode(Integer node1, Integer node2) {
        if (!nodes.containsKey(node1)) {
            nodes.put(node1, new ArrayList<>());
        }

        nodes.get(node1).add(node2);
    }
}

