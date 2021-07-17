package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Time time = Time.of(br.readLine());
        int totalMin = Integer.parseInt(br.readLine());

        int needHour = totalMin / 60;
        int needMin = totalMin - needHour * 60;

        time.addMin(needMin);
        time.addHour(needHour);

        System.out.println(time);
    }

    private static class Time {
        private int hour;
        private int min;

        public Time(int hour, int min) {
            this.hour = hour;
            this.min = min;
        }

        public static Time of(String stringTime) {
            StringTokenizer tokenizer = new StringTokenizer(stringTime);
            int hour = Integer.parseInt(tokenizer.nextToken());
            int min = Integer.parseInt(tokenizer.nextToken());
            return new Time(hour, min);
        }

        public void addMin(int min) {
            this.min += min;
            if (this.min >= 60) {
                addHour(1);
                this.min -= 60;
            }
        }

        public void addHour(int hour) {
            this.hour += hour;
            if (this.hour >= 24) {
                this.hour -= 24;
            }
        }

        @Override
        public String toString() {
            return this.hour + " " + this.min;
        }
    }
}
