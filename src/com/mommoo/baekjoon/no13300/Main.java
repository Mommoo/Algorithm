package com.mommoo.baekjoon.no13300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static final String GIRL = "0";
    public static final String BOY = "1";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int studentCount = Integer.parseInt(tokenizer.nextToken());
        int maxRoomValue = Integer.parseInt(tokenizer.nextToken());

        Map<Integer, People> classes = createClasses(reader, studentCount);

        int totalRoomCount = 0;
        for (int grade = 1; grade <= 6; grade++) {
            People people = classes.get(grade);
            if (people == null) {
                 continue;
            }

            totalRoomCount += people.computeNeedToRoomCount(maxRoomValue);
        }

        System.out.println(totalRoomCount);
    }

    private static Map<Integer, People> createClasses(BufferedReader reader, int studentCount) throws IOException {
        Map<Integer, People> classes = new HashMap<>();

        for (int i = 0 ; i < studentCount; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String sex = tokenizer.nextToken();
            Integer grade = Integer.parseInt(tokenizer.nextToken());

            if (classes.get(grade) == null) {
                classes.put(grade, new People());
            }

            People people = classes.get(grade);
            if (BOY.equals(sex)) {
                people.increaseBoyCount();
            }

            if (GIRL.equals(sex)) {
                people.increaseGirlCount();
            }
        }

        return classes;
    }

    private static class People {
        private int girlCount;
        private int boyCount;

        public void increaseBoyCount() {
            boyCount++;
        }

        public void increaseGirlCount() {
            girlCount++;
        }

        public int computeNeedToRoomCount(int maxRoomValue) {
            return computeRoomCount(girlCount, maxRoomValue) + computeRoomCount(boyCount, maxRoomValue);
        }

        private static int computeRoomCount(int peopleCount, int maxRoomValue) {
            if (peopleCount == 0) {
                return 0;
            }

            return (peopleCount / maxRoomValue) + (peopleCount % maxRoomValue == 0 ? 0 : 1);
        }
    }
}
