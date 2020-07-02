package com.mommoo.baekjoon.no11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        int numberCount = Integer.parseInt(br.readLine());
        PriorityQueue<Element> elements = new PriorityQueue<>();

        while (numberCount-- > 0) {
            int numberElement = Integer.parseInt(br.readLine());
            if (numberElement == 0) {
                Element element = elements.poll();
                builder.append(element == null ? 0 : element.value)
                       .append("\n");
                continue;
            }

            elements.offer(new Element(numberElement));
        }

        br.close();
        System.out.println(builder);
    }

    private static class Element implements Comparable<Element> {
        private final int value;

        public Element(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Element o) {
            int absSub = Math.abs(value) - Math.abs(o.value);
            if (absSub == 0) {
                return value - o.value;
            }

            return absSub;
        }
    }
}
