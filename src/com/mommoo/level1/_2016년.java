package com.mommoo.level1;

public class _2016년 {
    private static final int[] months = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

    public static void main(String[] args) {
        _2016년 instance = new _2016년();
        System.out.println(instance.solution(1, 1));
        System.out.println(instance.solution(1, 8));
        System.out.println(instance.solution(1, 31));
        System.out.println(instance.solution(2, 1));
        System.out.println(instance.solution(5, 24));
    }

    private static int computePreviousDates(int curMonth) {
        int dates = 0;
        for (int month = 1; month <= 12; month++) {
            if (month >= curMonth) {
                break;
            }
            dates += months[month];
        }

        return dates;
    }

    public String solution(int a, int b) {
        int totalPreviousDate = computePreviousDates(a);
        int totalDate = totalPreviousDate + b;
        int index = (totalDate - 1) % 7;
        int dayIndex = (index + 5) % 7;
        return days[dayIndex];
    }
}
