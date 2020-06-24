package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int maxNumber = Integer.parseInt(stringTokenizer.nextToken());
        int order = Integer.parseInt(stringTokenizer.nextToken());

        List<Integer> numbers = createNumbers(maxNumber);
        StringBuilder builder = new StringBuilder("<");
        int previousIndex = 0;
        while (!numbers.isEmpty()) {
            int nextNumber = numbers.size();
            previousIndex = (previousIndex + order - 1) % nextNumber;
            builder.append(numbers.remove(previousIndex)).append(numbers.isEmpty() ? ">" : ", ");
        }

        System.out.println(builder);
    }

    private static List<Integer> createNumbers(int maxNumber) {
        List<Integer> numbers = new ArrayList<>(maxNumber + 4);
        for (int number = 1; number <= maxNumber; number++) {
            numbers.add(number);
        }
        return numbers;
    }
}
