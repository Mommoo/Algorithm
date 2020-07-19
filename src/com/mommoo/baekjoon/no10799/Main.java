package com.mommoo.baekjoon.no10799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Lazers lazers = new Lazers();
        Loads loads = new Loads();

        Deque<Load> loadDeque = new LinkedList<>();
        String string = reader.readLine();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(' && string.charAt(i + 1) == ')') {
                lazers.add(new Lazer(i));
                i++;
                continue;
            }

            if (c == '(') {
                Load load = new Load(i);
                loadDeque.push(load);
            } else if (c == ')') {
                Load load = loadDeque.pop();
                load.endIndex = i;
                loads.add(load);
            }
        }

        while (lazers.isNotEmpty()) {
            Lazer lazer = lazers.popFirst();
            loads.affectLazer(lazer);
        }

        System.out.println(loads.computeCount());
    }

    private static class Lazers {
        private final LinkedList<Lazer> elements = new LinkedList<>();

        public void add(Lazer lazer) {
            elements.add(lazer);
        }

        public boolean isNotEmpty() {
            return !elements.isEmpty();
        }

        public Lazer popFirst() {
            return elements.removeFirst();
        }
    }

    private static class Lazer {
        private final int beginIndex;

        public Lazer(int beginIndex) {
            this.beginIndex = beginIndex;
        }
    }

    private static class Loads {
        private List<Load> elements = new LinkedList<>();

        public void add(Load load) {
            elements.add(load);
        }

        public void affectLazer(Lazer lazer) {
            for (Load load : elements) {
                load.affectLazer(lazer);
            }
        }

        public int computeCount() {
            int totalCount = 0;
            for (Load load: elements) {
                totalCount += load.count;
            }
            return totalCount;
        }
    }

    private static class Load {
        private final int beginIndex;
        private int endIndex;
        private int count = 1;

        public Load(int beginIndex) {
            this.beginIndex = beginIndex;
        }

        public void affectLazer(Lazer lazer) {
            if (beginIndex < lazer.beginIndex && lazer.beginIndex < endIndex) {
                count++;
            }
        }
    }
}
