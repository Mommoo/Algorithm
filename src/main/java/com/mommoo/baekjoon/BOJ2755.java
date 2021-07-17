package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ2755 {
    public static void main(String[] args) throws Exception {
        Map<String, Double> grades = createGrades();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int totalScore = 0;
        double totalGrade = 0;

        while (N-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            tokenizer.nextToken();
            int score = Integer.parseInt(tokenizer.nextToken());
            totalScore += score;
            totalGrade += score * grades.get(tokenizer.nextToken());
        }

        System.out.println(String.format("%.2f", totalGrade / totalScore));
    }

    private static Map<String, Double> createGrades() {
        Map<String, Double> grades = new HashMap<>();
        grades.put("A+", 4.3);
        grades.put("A0", 4.0);
        grades.put("A-", 3.7);
        grades.put("B+", 3.3);
        grades.put("B0", 3.0);
        grades.put("B-", 2.7);
        grades.put("C+", 2.3);
        grades.put("C0", 2.0);
        grades.put("C-", 1.7);
        grades.put("D+", 1.3);
        grades.put("D0", 1.0);
        grades.put("D-", 0.7);
        grades.put("F", 0.0);
        return grades;
    }
}
