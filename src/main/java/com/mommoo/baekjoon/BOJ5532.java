package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BOJ5532 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int remainDays = parseInt(reader);
        int totalKoreanPages = parseInt(reader);
        int totalMathPages = parseInt(reader);
        int dailyKoreanPage = parseInt(reader);
        int dailyMathPage = parseInt(reader);

        Work koreanWork = new Work(dailyKoreanPage, totalKoreanPages);
        Work mathWork = new Work(dailyMathPage, totalMathPages);

        Works works = new Works(koreanWork, mathWork);

        while (works.isRemainWork()) {
            works.processAll();
            remainDays--;
        }

        System.out.println(remainDays);
    }

    private static int parseInt(BufferedReader reader) throws Exception {
        return Integer.parseInt(reader.readLine());
    }

    private static class Works {
        private final List<Work> elements;

        public Works(Work... elements) {
            this.elements = Stream.of(elements)
                                  .collect(Collectors.toList());
        }

        public void processAll() {
            this.elements.forEach(Work::process);
            this.elements.removeIf(Work::isDone);
        }

        public boolean isRemainWork() {
            return !this.elements.isEmpty();
        }
    }

    private static class Work {
        private final int workAmountPerDay;
        private int totalAmount;

        public Work(int workAmountPerDay, int totalAmount) {
            this.workAmountPerDay = workAmountPerDay;
            this.totalAmount = totalAmount;
        }

        public boolean isDone() {
            return totalAmount == 0;
        }

        public void process() {
            totalAmount = Math.max(0, totalAmount - workAmountPerDay);
        }
    }
}
