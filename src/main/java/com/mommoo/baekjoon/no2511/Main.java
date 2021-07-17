package com.mommoo.baekjoon.no2511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final StringBuilder BUILDER = new StringBuilder();
    private static final int[][] CARD = new int[2][10];

    public static void main(String[] args) throws IOException {
        setUp();

        int aScore = 0;
        int bScore = 0;
        int isLastWinType = -1;
        for (int i = 0 ; i < 10; i++) {
            if (CARD[0][i] > CARD[1][i]) {
                aScore += 3;
                isLastWinType = 0;
            } else if (CARD[0][i] == CARD[1][i]) {
                aScore++;
                bScore++;
            } else {
                bScore += 3;
                isLastWinType = 1;
            }
        }

        BUILDER.append(aScore).append(' ').append(bScore).append('\n');
        if (aScore != bScore) {
            BUILDER.append(aScore > bScore ? 'A' : 'B');
            System.out.println(BUILDER);
            return;
        }

        if (isLastWinType != -1) {
            BUILDER.append(isLastWinType == 0 ? 'A' : 'B');
            System.out.println(BUILDER);
            return;
        }

        BUILDER.append('D');
        System.out.println(BUILDER);

    }

    private static void setUp() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int type = 0 ; type < 2; type++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < 10; i++) {
                CARD[type][i] = tokenizer.nextToken().charAt(0) - '0';
            }
        }

        reader.close();
    }
}
