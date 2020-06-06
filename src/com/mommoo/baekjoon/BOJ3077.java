package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ3077 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int eventCount = Integer.parseInt(br.readLine());
        Map<String, Integer> eventOrders = createEventOrders(br.readLine());

        int score = 0;
        String[] events = br.readLine().split(" ");
        int[] orders = new int[eventCount];
        for (int i = 0 ; i < eventCount; i++) {
            orders[i] = eventOrders.get(events[i]);
        }
        for (int i = 0; i < eventCount; i++) {
            for (int j = i + 1; j < eventCount; j++) {
                if (orders[i] < orders[j]) {
                    score++;
                }
            }
        }

        System.out.println(score + "/" + eventCount * (eventCount - 1) / 2);
    }

    private static Map<String, Integer> createEventOrders(String events) {
        Map<String, Integer> eventOrders = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(events);
        int order = 1;
        while (tokenizer.hasMoreTokens()) {
            eventOrders.put(tokenizer.nextToken(), order++);
        }
        return eventOrders;
    }
}
