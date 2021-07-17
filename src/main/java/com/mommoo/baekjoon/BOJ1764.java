package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1764 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Set<String> finder = new HashSet<>();
        while (N-- > 0) {
            finder.add(reader.readLine());
        }

        List<String> results = new LinkedList<>();
        while (M-- > 0) {
            String person = reader.readLine();
            if (!finder.contains(person)) {
                continue;
            }

            results.add(person);
        }
        reader.close();
        Collections.sort(results);

        System.out.println(results.size());
        System.out.println(String.join("\n", results));
    }
}
