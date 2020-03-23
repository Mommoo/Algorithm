package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ11586 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        List<String> lines = new ArrayList<>();
        while (N-- > 0) {
            lines.add(reader.readLine());
        }

        int status = Integer.parseInt(reader.readLine());
        int delta = status == 3 ? -1 : 1;
        int destRow = status == 3 ? 0 : lines.size() - 1;
        int curRow = status == 3 ? lines.size() : -1;

        boolean isReverse = status == 2;

        StringBuilder builder = new StringBuilder();
        while (curRow != destRow) {
            curRow += delta;
            String line = lines.get(curRow);
            if (isReverse) {
                line = reverse(line);
            }
            builder.append(line)
                   .append(System.lineSeparator());
        }

        System.out.println(builder);
    }

    private static String reverse(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            builder.append(str.charAt(i));
        }
        return builder.toString();
    }
}
