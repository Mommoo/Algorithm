package com.mommoo.baekjoon.no2947;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        int[] woods = getWoods();
        for (int i = 0; i < 5; i++) {
            boolean isSwap = false;
            for (int j = 0; j < 4 - i; j++) {
                if (woods[j] > woods[j+1]) {
                    isSwap = true;
                    swapAndWrite(woods, j, j+1);
                }
            }

            if (!isSwap) {
                break;
            }
        }

        System.out.println(BUILDER);
    }

    public static void swapAndWrite(int[] woods, int from, int to) {
        swap(woods, from, to);
        write(woods);
    }

    public static void write(int[] woods) {
        BUILDER.append(woods[0]);
        for (int i = 1; i < 5; i ++) {
            BUILDER.append(SPACE).append(woods[i]);
        }
        BUILDER.append(NEW_LINE);
    }

    public static void swap(int[] woods, int from, int to) {
        int tmp = woods[from];
        woods[from] = woods[to];
        woods[to] = tmp;
    }

    public static int[] getWoods() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());
        br.close();

        int[] woods = new int[5];
        for (int i = 0; i < 5; i++) {
            woods[i] = input.nextToken().charAt(0) - '0';
        }

        return woods;
    }
}
