package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12790 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int TC =  Integer.parseInt(reader.readLine());
        while (TC -- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int HP = nextInt(tokenizer);
            int MP = nextInt(tokenizer);
            int ATT = nextInt(tokenizer);
            int DEF = nextInt(tokenizer);

            HP = Math.max(1, HP + nextInt(tokenizer));
            MP = Math.max(1, MP + nextInt(tokenizer));
            ATT = Math.max(0, ATT + nextInt(tokenizer));
            DEF += nextInt(tokenizer);

            int POWER = 1 * (HP) + 5 * (MP) + 2 * (ATT) + 2 * (DEF);
            System.out.println(POWER);
        }
    }

    private static Integer nextInt(StringTokenizer tokenizer) {
        return Integer.parseInt(tokenizer.nextToken());
    }
}
