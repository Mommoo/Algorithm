package com.mommoo.programmers.exam.day1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Solution3 {
    public static void main(String[] args) {
        System.out.println(new Solution3().solution(new int[]{4, 3, 1, 2, 5}));
        System.out.println(new Solution3().solution(new int[]{5, 4, 3, 2, 1}));
    }

    public int solution(int[] order) {
        int index = 0;
        int startBoxSeq = order[index++];
        Stack<Integer> stack = new Stack<>();
        for (int boxSeq = 0; boxSeq < startBoxSeq-1; boxSeq++) {
            stack.push(order[boxSeq]);
        }

        int answer = 1;

        while (index < order.length) {
            if (!stack.isEmpty()) {
                System.out.println("::"+stack.peek());
            }
            if (!stack.isEmpty() && (stack.peek() == answer+1)) {
                stack.pop();
                answer++;
            }

            index++;
        }


        return answer;
    }

    private static class Box {
        private final int seqNum;
        private final int order;

        public Box(int seqNum, int order) {
            this.seqNum = seqNum;
            this.order = order;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "seqNum=" + seqNum +
                    ", order=" + order +
                    '}';
        }
    }
}
