package com.mommoo.programmers.exam.day1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public static void main(String[] args) {
        String[] want1 = {"banana", "apple", "rice", "pork", "pot"};
        int[] number1 = {3, 2, 2, 2, 1};
        String[] discount1 = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        System.out.println(new Solution2().solution(want1, number1, discount1));
    }

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int days = 0;
        for (int count: number) {
            days += count;
        }

        Map<String, Integer> counts = new HashMap<>();

        int maxTryDay = discount.length - days + 1;
        for (int day = 0; day < maxTryDay; day++) {
            counts.clear();
            for (int i = day; i < days + day; i++) {
                String dis = discount[i];
                int prevCnt = counts.getOrDefault(dis, 0);
                counts.put(dis, prevCnt + 1);
            }

            if (doesNotAllBuy(want, number, counts)) {
                continue;
            }

            answer++;
        }


        return answer;
    }

    private static boolean doesNotAllBuy(String[] want, int[] number, Map<String, Integer> counts) {
        for (int i = 0 ; i < number.length; i++) {
            if (counts.getOrDefault(want[i], 0) < number[i]) {
                return true;
            }
        }

        return false;
    }

    public int solution2(String[] want, int[] number, String[] discount) {
        Map<String, int[]> accumlators = new HashMap<>();
        for (String thing : want) {
            accumlators.put(thing, new int[discount.length]);
        }

        for (int day = discount.length - 1; day >= 0; day--) {
            String discountThing = discount[day];
            if (!accumlators.containsKey(discountThing)) {
                continue;
            }

            accumlators.get(discountThing)[day]++;

            if (day == 0) {
                continue;
            }

            for (String key : accumlators.keySet()) {
                int[] accumulator = accumlators.get(key);
                accumulator[day-1] += accumulator[day];
            }
        }

        for (String key: accumlators.keySet()) {
            System.out.println(key+":"+ Arrays.toString(accumlators.get(key)));
        }

        int answer = 0;
        for (int day = 0; day < discount.length; day++) {
            if (doesNotBuyAll(day, want, number, accumlators)) {
                continue;
            }

            answer++;
        }

        return answer;
    }

    private static boolean doesNotBuyAll(int day, String[] want, int[] number, Map<String, int[]> accumulators) {
        for (int i = 0; i < number.length; i++) {
            String wantThing = want[i];
            int wantNum = number[i];

            int snapShotCount = accumulators.get(wantThing)[day];
            if (snapShotCount < wantNum) {
                return true;
            }
        }

        return false;
    }
}
