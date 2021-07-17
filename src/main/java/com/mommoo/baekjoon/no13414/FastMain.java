package com.mommoo.baekjoon.no13414;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class FastMain {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int count = Integer.parseInt(tokenizer.nextToken());
        int inputCount = Integer.parseInt(tokenizer.nextToken());

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        while (inputCount-- > 0) {
            String input = reader.readLine();
            linkedHashSet.remove(input);
            linkedHashSet.add(input);
        }

        Iterator<String> iterator = linkedHashSet.iterator();
        while (count -- > 0 && iterator.hasNext()) {
            write(iterator.next());
        }

        reader.close();
        System.out.println(BUILDER);
    }

    private static void write(String value) {
        BUILDER.append(value).append('\n');
    }
}
