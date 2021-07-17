package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        br.readLine();

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        Set<Long> hasCardNumbers = new HashSet<>();
        while (tokenizer.hasMoreTokens()) {
            hasCardNumbers.add(Long.parseLong(tokenizer.nextToken()));
        }

        br.readLine();
        tokenizer = new StringTokenizer(br.readLine());
        while (tokenizer.hasMoreTokens()) {
            Long targetCardNum = Long.parseLong(tokenizer.nextToken());
            if (hasCardNumbers.contains(targetCardNum)) {
                bw.write("1 ");
            } else {
                bw.write("0 ");
            }
        }

        bw.close();
        br.close();
    }
}
