package com.mommoo.programmers.level2;

import java.util.Arrays;

public class 구명보트 {
    public static void main(String[] args) {
        System.out.println(new 구명보트().solution(new int[]{70, 50, 80, 50}, 100));
        System.out.println(new 구명보트().solution(new int[]{70, 80, 50}, 100));
        System.out.println(new 구명보트().solution(new int[]{70, 80, 50}, 150));
    }

    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        int answer = 0;

        while (left < right) {
            int minWeight = people[left];
            int maxWeight = people[right];

            if (minWeight + maxWeight <= limit) {
                left++;
            }

            right--;
            answer++;
        }
        return answer + (left == right ? 1 : 0);
    }
}
