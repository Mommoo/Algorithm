package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ2606 {
    public static void main(String[] args) throws Exception {
        InputData inputData = new InputData();
        DisjointSet disjointSet = new DisjointSet(inputData.computerNumber);

        for (String pair : inputData.computerPairs) {
            String[] elements = pair.split(" ");
            int element1 = Integer.parseInt(elements[0]);
            int element2 = Integer.parseInt(elements[1]);
            disjointSet.union(element1, element2);
        }

        System.out.println(disjointSet.countGroup(1) - 1);
    }

    private static class InputData {
        private final int computerNumber;
        private final List<String> computerPairs;

        public InputData() throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            this.computerNumber = Integer.parseInt(reader.readLine());

            int pairCount = Integer.parseInt(reader.readLine());
            this.computerPairs = new ArrayList<>(pairCount);

            while (pairCount-- > 0) {
                this.computerPairs.add(reader.readLine());
            }

            reader.close();
        }
    }

    private static class DisjointSet {
        private final int[] elements;

        public DisjointSet(int elementCount) {
            this.elements = new int[elementCount + 1];
            for (int idx = 1; idx < elementCount + 1; idx++) {
                this.elements[idx] = idx;
            }
        }

        public int findRoot(int element) {
            if (elements[element] == element) {
                return element;
            }

            elements[element] = findRoot(elements[element]);

            return elements[element];
        }

        public void union(int element1, int element2) {
            int root1 = findRoot(element1);
            int root2 = findRoot(element2);
            int target = Math.min(root1, root2);

            elements[root1] = target;
            elements[root2] = target;
            elements[element1] = target;
            elements[element2] = target;
        }

        public int countGroup(int element) {
            int root = findRoot(element);
            return (int) Arrays.stream(this.elements)
                               .filter(value -> root == findRoot(value))
                               .count();
        }
    }
}
