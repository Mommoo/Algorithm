package com.mommoo.baekjoon.no20291;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> counter = new HashMap<>();
        int count = Integer.parseInt(br.readLine());

        while (count-- > 0) {
            String filename = br.readLine();
            String extensions = filename.substring(filename.indexOf(".") + 1);
            counter.put(extensions, counter.getOrDefault(extensions, 0) + 1);
        }

        br.close();

        List<String> fileExtensions = new ArrayList<>(counter.keySet());
        Collections.sort(fileExtensions);

        StringBuilder builder = new StringBuilder();

        for (String fileExtension : fileExtensions) {
            builder.append(fileExtension).append(" ").append(counter.get(fileExtension))
                   .append("\n");
        }

        System.out.println(builder.toString());
    }
}
