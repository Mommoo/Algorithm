package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5046 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int personCount = Integer.parseInt(tokenizer.nextToken());
        int totalBudget = Integer.parseInt(tokenizer.nextToken());
        int hotelCount = Integer.parseInt(tokenizer.nextToken());
        int weekendCount = Integer.parseInt(tokenizer.nextToken());

        int minExpectBudget = Integer.MAX_VALUE;
        while (hotelCount-- > 0) {
            int onePersonHotelPrice = Integer.parseInt(br.readLine());
            if (onePersonHotelPrice * personCount > totalBudget) {
                br.readLine();
                continue;
            }

            String[] weekendCanPersonSleepCounts = br.readLine().split(" ");
            int usableWeekendCount = Math.min(weekendCanPersonSleepCounts.length, weekendCount);
            for (int i = 0; i < usableWeekendCount; i++) {
                int canPersonSleepCount = Integer.parseInt(weekendCanPersonSleepCounts[i]);
                if (canPersonSleepCount < personCount) {
                    continue;
                }

                minExpectBudget = Math.min(minExpectBudget, onePersonHotelPrice * personCount);
            }
        }

        System.out.println(minExpectBudget == Integer.MAX_VALUE ? "stay home" : minExpectBudget);
    }
}
