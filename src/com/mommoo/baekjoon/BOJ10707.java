package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10707 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int xMoneyPer1 = parseInt(reader);
        int yBasicMoney = parseInt(reader);
        int yLimitBasicLiter = parseInt(reader);
        int yMoneyPer1 = parseInt(reader);
        int usage = parseInt(reader);

        System.out.println(Math.min(usage * xMoneyPer1, computeYUsageTotalMoney(usage, yBasicMoney, yLimitBasicLiter, yMoneyPer1)));
    }

    private static int parseInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int computeYUsageTotalMoney(int totalUsage, int yBasicMoney, int yLimitBasicLiter, int yMoneyPer1) {
        int totalMoney = yBasicMoney;
        if (yLimitBasicLiter < totalUsage) {
            totalMoney += (totalUsage - yLimitBasicLiter) * yMoneyPer1;
        }
        return totalMoney;
    }
}
