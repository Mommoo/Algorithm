package com.mommoo.programmers.level3;

import java.util.PriorityQueue;

public class 숫자_게임 {
    public static void main(String[] args) {
        System.out.println(new 숫자_게임().solution(new int[]{5, 1, 3, 7}, new int[]{2, 2, 6 ,8}));
        System.out.println(new 숫자_게임().solution(new int[]{2, 2, 2, 2}, new int[]{1, 1, 1, 1}));
        System.out.println(new 숫자_게임().solution(new int[]{2, 4, 6, 8}, new int[]{1, 3, 5, 7}));
    }

    public int solution(int[] A, int[] B) {
        PriorityQueue<Integer> aPriorityQueue = createPriorityQueue(A);
        PriorityQueue<Integer> bPriorityQueue = createPriorityQueue(B);

        int answer = 0;

        while (!bPriorityQueue.isEmpty()) {
            if (aPriorityQueue.peek() < bPriorityQueue.poll()){
                aPriorityQueue.poll();
                answer++;
            }
        }

        return answer;
    }

    private static PriorityQueue<Integer> createPriorityQueue(int[] A) {
        PriorityQueue<Integer> aPriorityQueue = new PriorityQueue<>();
        for (int value : A) {
            aPriorityQueue.offer(value);
        }
        return aPriorityQueue;
    }
}
