package com.mommoo.programmers.level3;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class 네트워크 {
    public static void main(String[] args) {
        int[][] computers1 = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int[][] computers2 = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        int[][] computers3 = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        System.out.println(new 네트워크().solution(3, computers1));
        System.out.println(new 네트워크().solution(3, computers2));
        System.out.println(new 네트워크().solution(3, computers3));
    }

    public int solution(int n, int[][] computers) {
        Set<Integer> visitorSet = new HashSet<>();
        int answer = 0;
        for (int node = 0; node < n ; node++) {
            if (visitorSet.contains(node)) {
                continue;
            }

            Stack<Integer> toStack = new Stack<>();
            toStack.push(node);
            while (!toStack.isEmpty()) {
                int startNode = toStack.pop();
                if (visitorSet.contains(startNode)) {
                    continue;
                }

                visitorSet.add(startNode);
                for (int to = 0; to < n; to++) {
                    if (computers[startNode][to] == 0 || visitorSet.contains(to)) {
                        continue;
                    }
                    toStack.push(to);
                }
            }

            answer++;
        }
        return answer;
    }
}
