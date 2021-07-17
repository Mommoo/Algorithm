package com.mommoo.programmers.level1;

import java.util.Scanner;

public class 직사각형_별찍기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        String line = computeLine(a);
        while (b-- > 0) {
            System.out.println(line);
        }
    }

    private static String computeLine(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- > 0) {
            builder.append("*");
        }

        return builder.toString();
    }
}
