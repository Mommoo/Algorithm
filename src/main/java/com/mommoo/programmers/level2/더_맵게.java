package com.mommoo.programmers.level2;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class 더_맵게 {
    public static void main(String[] args) {
        System.out.println(new 더_맵게().solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> priorityQueue = Arrays.stream(scoville)
                                                     .boxed()
                                                     .collect(Collectors.toCollection(PriorityQueue::new));

        int answer = 0;
        while (priorityQueue.size() >= 2 && priorityQueue.peek() < K) {
            int newK = priorityQueue.poll() + priorityQueue.poll() * 2;
            priorityQueue.add(newK);
            answer++;
        }

        if (priorityQueue.isEmpty() || priorityQueue.peek() < K) {
            return -1;
        }

        return answer;
    }
}
