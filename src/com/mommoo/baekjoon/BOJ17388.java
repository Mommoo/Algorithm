package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class BOJ17388 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        Participant soongsil = new Participant("Soongsil", Integer.parseInt(tokenizer.nextToken()));
        Participant korea = new Participant("Korea", Integer.parseInt(tokenizer.nextToken()));
        Participant hanyang = new Participant("Hanyang", Integer.parseInt(tokenizer.nextToken()));

        if (Stream.of(soongsil, korea, hanyang).mapToInt(p -> p.value).sum() >= 100) {
            System.out.println("OK");
            return ;
        }

        System.out.println(Stream.of(soongsil, korea, hanyang)
                                 .min(Participant::compareTo)
                                 .get().name);
    }

    private static class Participant implements Comparable<Participant> {
        private final String name;
        private final int value;

        public Participant(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public int compareTo(Participant o) {
            return this.value - o.value;
        }
    }
}
