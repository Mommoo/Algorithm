package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ10709 {
    private static final char CLOUD = 'c';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int rawSize = Integer.parseInt(tokenizer.nextToken());
        int colSize = Integer.parseInt(tokenizer.nextToken());

        for (int raw = 0; raw < rawSize; raw++) {
            String mapLine = br.readLine();
            int count = 0;
            for (int col = 0 ; col < colSize; col++) {
                char mapNode = mapLine.charAt(col);
                if (mapNode == CLOUD) {
                    bw.write("0 ");
                    count = 1;
                } else if (count != 0) {
                    bw.write(count+ " ");
                    count++;
                } else {
                    bw.write("-1 ");
                }
            }
            bw.newLine();
        }

        bw.close();
        br.close();
    }
}
