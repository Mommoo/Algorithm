package com.mommoo.programmers.level3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _셔틀버스 {
    public static void main(String[] args) {
        System.out.println(new _셔틀버스().solution(1, 1, 1, new String[]{"24:00", "09:02"}));
        System.out.println(new _셔틀버스().solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(new _셔틀버스().solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
        System.out.println(new _셔틀버스().solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        System.out.println(new _셔틀버스().solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        System.out.println(new _셔틀버스().solution(1, 1, 1, new String[]{"23:59"}));
        System.out.println(new _셔틀버스().solution(10, 60, 45, new String[]{"23:59", "23:59", "23:59", "23:59", "23:59",
                "23:59", "23:59", "23:59", "23:59", "23:59",
                "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }

    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        Queue<LocalTime> times = computeTimes(timetable);
        Bus bus = Bus.first(m);
        for (int i = 0; i < n; i++) {
            while (!times.isEmpty() && bus.canTake(times.peek())) {
                bus.take(times.poll());
            }

            if (i + 1 != n) {
                bus = bus.next(t);
            }
        }

        if (bus.isRemainSeat()) {
            return computeAnswer(bus.beginTime);
        } else  {
            return computeLastTakeTime(bus);
        }
    }

    private static LocalTime computeTime(String stringTime) {
        int hour = Integer.parseInt(stringTime.substring(0, 2));
        int min = Integer.parseInt(stringTime.substring(3, 5));
        if (hour == 24) {
            hour = 23;
            min = 59;
        }
        return LocalTime.of(hour, min);
    }

    private static Queue<LocalTime> computeTimes(String[] timetables) {
        Queue<LocalTime> times = new LinkedList<>();
        for (String time: timetables) {
            times.add(computeTime(time));
        }
        return times;
    }

    private static String computeAnswer(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private static String computeAnswerMinusOneMin(LocalTime time) {
        if (time.getHour() == 0 && time.getMinute() == 1) {
            return "00:00";
        }

        return computeAnswer(time.minusMinutes(1));
    }

    private static String computeLastTakeTime(Bus bus) {
        List<LocalTime> takes = bus.takes;
        LocalTime maxTime = takes.get(0);

        for (int i = 1; i < takes.size(); i++) {
            LocalTime time = takes.get(i);
            if (time.isAfter(maxTime)) {
                maxTime = time;
            }
        }

        return computeAnswerMinusOneMin(maxTime);
    }

    private static class Bus {
        private final int maxSeat;
        private final LocalTime beginTime;
        private final List<LocalTime> takes = new ArrayList<>();

        public Bus(int maxSeat, LocalTime beginTime) {
            this.maxSeat = maxSeat;
            this.beginTime = beginTime;
        }

        public static Bus first(int maxSeat) {
            return new Bus(maxSeat, LocalTime.of(9, 0));
        }

        public Bus next(int additionalMin) {
            return new Bus(maxSeat, beginTime.plusMinutes(additionalMin));
        }

        public boolean canTake(LocalTime time) {
            return isRemainSeat() && (beginTime.isAfter(time) || beginTime.equals(time));
        }

        public boolean isRemainSeat() {
            return takes.size() < maxSeat;
        }

        public void take(LocalTime time) {
            takes.add(time);
        }
    }
}
