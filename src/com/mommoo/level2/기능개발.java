package com.mommoo.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 기능개발 {
    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        int[] a = new 기능개발().solution(progresses, speeds);
        System.out.println(Arrays.toString(a));
    }

    private static Queue<Integer> computeWorkingDaysQueue(int[] progresses, int[] speeds) {
        Queue<Integer> workingDaysQueue = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            float remainProgress = 100f - progresses[i];
            int endDay = (int) Math.ceil(remainProgress / (float) speeds[i]);
            workingDaysQueue.add(endDay);
        }

        return workingDaysQueue;
    }

    private static void increaseCurrentWorkingDay(List<Integer> workingDays) {
        int currentIndex = workingDays.size() - 1;
        int increaseDay = workingDays.get(currentIndex) + 1;
        workingDays.set(currentIndex, increaseDay);
    }

    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> workingDaysQueue = computeWorkingDaysQueue(progresses, speeds);

        List<Integer> workingDays = new ArrayList<>();
        int previousWorkingDay = workingDaysQueue.poll();
        workingDays.add(1);

        while (!workingDaysQueue.isEmpty()) {
            int currentWorkingDay = workingDaysQueue.poll();
            if (currentWorkingDay <= previousWorkingDay) {
                increaseCurrentWorkingDay(workingDays);
            } else {
                previousWorkingDay = currentWorkingDay;
                workingDays.add(1);
            }
        }

        return workingDays.stream()
                          .mapToInt(day -> day)
                          .toArray();
    }
}
