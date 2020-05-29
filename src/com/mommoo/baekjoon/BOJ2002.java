package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Objects;

public class BOJ2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int carCount = Integer.parseInt(br.readLine());

        int answer = 0;
        LinkedHashSet<String> orderedCar = new LinkedHashSet<>();
        for (int i = 0; i < carCount; i++) {
            orderedCar.add(br.readLine());
        }

        for (int i = 0; i < carCount; i++) {
            String needToFindCarCode = br.readLine();
            String nextOrderCarCode = orderedCar.iterator().next();

            if (!Objects.equals(nextOrderCarCode, needToFindCarCode)) {
                answer++;
            }

            orderedCar.remove(needToFindCarCode);
        }

        System.out.println(answer);
    }
}
