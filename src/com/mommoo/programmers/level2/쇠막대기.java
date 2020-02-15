package com.mommoo.programmers.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class 쇠막대기 {
    public static void main(String[] args) {
        System.out.println(new 쇠막대기().solution("()(((()())(())()))(())"));
    }

    private static boolean isLaser(int open, int close) {
        return open + 1 == close;
    }

    public int solution(String arrangement) {
        Stack<Integer> openIndexStack = new Stack<>();
        Queue<Integer> laserQueue = new LinkedList<>();
        List<Rod> rods = new ArrayList<>();

        for (int i = 0; i < arrangement.length(); i++) {
            char c = arrangement.charAt(i);

            if (c == '(') {
                openIndexStack.push(i);
                continue;
            }

            int openIndex = openIndexStack.pop();

            if (isLaser(openIndex, i)) {
                laserQueue.offer(i);
            } else {
                rods.add(new Rod(openIndex, i));
            }
        }

        while (!laserQueue.isEmpty()) {
            int laserIndex = laserQueue.poll();
            rods.forEach(rod -> rod.chopByLaser(laserIndex));
        }

        return rods.stream()
                   .mapToInt(Rod::count)
                   .sum();
    }

    private static class Rod {
        private final int beginIndex;
        private final int endIndex;
        private int rodCount = 1;

        public Rod(int beginIndex, int endIndex) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }

        public void chopByLaser(int laserIndex) {
            if (beginIndex < laserIndex && laserIndex < endIndex) {
                rodCount++;
            }
        }

        public int count() {
            return rodCount;
        }
    }
}
