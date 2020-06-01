package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ10546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int marathonerCount = Integer.parseInt(br.readLine());
        Map<String, Integer> marathoners = new HashMap<>();
        for(int i = 0; i < marathonerCount; i++) {
            String marathoner = br.readLine();
            int count = marathoners.getOrDefault(marathoner, 0);
            marathoners.put(marathoner, count + 1);
        }

        for (int i = 0; i < marathonerCount - 1; i++) {
            String enteredMarathoner = br.readLine();
            int decreasedCount = marathoners.get(enteredMarathoner) - 1;
            marathoners.put(enteredMarathoner, decreasedCount);
            if (decreasedCount == 0) {
                marathoners.remove(enteredMarathoner);
            }
        }

        System.out.println(marathoners.keySet().iterator().next());
    }
}
