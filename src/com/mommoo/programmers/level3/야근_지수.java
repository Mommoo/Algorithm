package com.mommoo.programmers.level3;

import java.util.*;
import java.util.stream.Collectors;

public class 야근_지수 {
    public static void main(String[] args) {
        System.out.println(new 야근_지수().solution(4, new int[]{4, 3, 3}));
        System.out.println(new 야근_지수().solution(1, new int[]{2, 1, 2}));
        System.out.println(new 야근_지수().solution(3, new int[]{1, 1}));
    }

    public long solution(int n, int[] intWorks) {
        Works works = Works.from(intWorks);

        while (n-- > 0) {
            works.reduceOneHourAtMaxWorkTime();
        }

        return works.computeOvertimeValue();
    }

    private static class Works {
        private final PriorityQueue<Integer> workTimes;

        private Works(PriorityQueue<Integer> workTimes) {
            this.workTimes = workTimes;
        }

        public static Works from(int[] works) {
            Comparator<Integer> comparator = Comparator.reverseOrder();

            PriorityQueue<Integer> workTimes = Arrays.stream(works)
                                                     .boxed()
                                                     .collect(Collectors.toCollection(() -> new PriorityQueue<>(comparator)));

            return new Works(workTimes);
        }

        public void reduceOneHourAtMaxWorkTime() {
            Integer maxWorkTime = workTimes.poll();
            Integer remainWorkTime = Math.max(0, maxWorkTime - 1);
            workTimes.offer(remainWorkTime);
        }

        public long computeOvertimeValue() {
            long value = 0;

            while (!workTimes.isEmpty()) {
                value += Math.pow(workTimes.poll(), 2);
            }

            return value;
        }
    }
}
