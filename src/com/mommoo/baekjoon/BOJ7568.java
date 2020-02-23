package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ7568 {
    public static void main(String[] args) throws Exception {
        InputData inputData = new InputData();
        List<Spec> specs = inputData.specs.stream()
                                          .map(Spec::from)
                                          .collect(Collectors.toList());

        String result = IntStream.range(0, specs.size())
                                 .map(idx -> computeCountBiggerThanSelf(idx, specs))
                                 .map(count -> count + 1)
                                 .mapToObj(String::valueOf)
                                 .collect(Collectors.joining(" "));

        System.out.println(result);
    }

    private static int computeCountBiggerThanSelf(int currentIndex, List<Spec> spec) {
        Spec currentSpec = spec.get(currentIndex);
        return (int) IntStream.range(0, spec.size())
                              .filter(idx -> currentIndex != idx)
                              .mapToObj(spec::get)
                              .filter(currentSpec::isSmallerThan)
                              .count();
    }

    private static class InputData {
        private final List<String> specs;

        public InputData() throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int N = Integer.parseInt(reader.readLine());
            this.specs = new ArrayList<>(N);

            while (N-- > 0) {
                this.specs.add(reader.readLine());
            }

            reader.close();
        }

        public int size() {
            return specs.size();
        }
    }

    private static class Spec {
        private final int weight;
        private final int height;

        public Spec(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }

        public static Spec from(String spec) {
            String[] elements = spec.split(" ");
            int weight = Integer.parseInt(elements[0]);
            int height = Integer.parseInt(elements[1]);
            return new Spec(weight, height);
        }

        public boolean isSmallerThan(Spec spec) {
            return this.weight < spec.weight && this.height < spec.height;
        }
    }
}
