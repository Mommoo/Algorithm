package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ3182 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] checks = new boolean[N];
        int[] personMapper = createPersonMappers(N, br);
        br.close();

        int personIndex = -1;
        int maxRotateNumber = -1;
        for (int i = 0 ; i < N; i++) {
            Arrays.fill(checks, false);
            int nextIndex = i;
            int rotateCount = 0;
            while (!checks[nextIndex]) {
                checks[nextIndex] = true;
                nextIndex = personMapper[nextIndex];
                rotateCount++;
            }

            if (maxRotateNumber < rotateCount) {
                personIndex = i;
                maxRotateNumber = rotateCount;
            }
        }

        System.out.println(personIndex + 1);
    }

    private static int[] createPersonMappers(int N, BufferedReader br) throws IOException {
        int[] personMapper = new int[N];

        for (int i = 0 ; i < N; i++) {
            personMapper[i] = Integer.parseInt(br.readLine()) - 1;
        }

        return personMapper;
    }
}
