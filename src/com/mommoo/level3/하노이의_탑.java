package com.mommoo.level3;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class 하노이의_탑 {
    public static void main(String[] args) {
        System.out.println(arrayToString(new 하노이의_탑().solution(2)));
        System.out.println(arrayToString(new 하노이의_탑().solution(3)));
        System.out.println(arrayToString(new 하노이의_탑().solution(4)));
    }

    public int[][] solution(int n) {
        List<Hanoi> hanoiOrders = new LinkedList<>();
        Stack<Hanoi> hanoiQueue = readyHanoiStack(n);

        while (!hanoiQueue.isEmpty()) {
            Hanoi hanoi = hanoiQueue.pop();

            if (hanoi.canMove()) {
                hanoiOrders.add(hanoi);
            }

            List<Hanoi> nextHanois = hanoi.nextHanois();

            for (int i = nextHanois.size() - 1; i >= 0; i--) {
                Hanoi next = nextHanois.get(i);
                hanoiQueue.push(next);
            }
        }

        return toArray(hanoiOrders);
    }

    private static String arrayToString(int[][] array) {
        return Arrays.stream(array)
                     .map(Arrays::toString)
                     .collect(Collectors.joining("->"));
    }

    private static Stack<Hanoi> readyHanoiStack(int stickNum) {
        Stack<Hanoi> hanoiQueue = new Stack<>();
        hanoiQueue.push(Hanoi.first(stickNum));
        return hanoiQueue;
    }

    private static int[][] toArray(List<Hanoi> hanois) {
        int[][] array = new int[hanois.size()][2];
        int index = 0;
        for (Hanoi hanoi : hanois) {
            array[index++] = new int[]{hanoi.from, hanoi.to};
        }
        return array;
    }

    private static class Hanoi {
        private final int from;
        private final int to;
        private final int stickNum;

        public Hanoi(int from, int to, int stickNum) {
            this.from = from;
            this.to = to;
            this.stickNum = stickNum;
        }

        private static Hanoi first(int stickNum) {
            return new Hanoi(1, 3, stickNum);
        }

        public int getRemain() {
            return 6 - from - to;
        }

        public boolean canMove() {
            return stickNum == 1;
        }

        public List<Hanoi> nextHanois() {
            if (stickNum <= 1) {
                return Collections.emptyList();
            } else {
                int remain = getRemain();
                Hanoi moveToRemain = new Hanoi(from, remain, stickNum - 1);
                Hanoi moveToLast = new Hanoi(from, to, 1);
                Hanoi remainMoveToLast = new Hanoi(remain, to, stickNum - 1);
                return Arrays.asList(moveToRemain, moveToLast, remainMoveToLast);
            }
        }
    }
}
