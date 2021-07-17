package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ8979 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int nationCount = Integer.parseInt(tokenizer.nextToken());
        int findNation = Integer.parseInt(tokenizer.nextToken());

        List<NationMedal> nationMedals = IntStream.range(0, nationCount)
                                                  .mapToObj(idx -> createNationMedalFrom(reader))
                                                  .sorted()
                                                  .collect(Collectors.toList());

        if (nationMedals.get(0).isEqualsNationCode(findNation)) {
            System.out.println(1);
            return;
        }

        int nextRank = 1;
        for (int i = 1; i < nationCount; i++) {
            NationMedal previousMedal = nationMedals.get(i - 1);
            NationMedal currentMedal = nationMedals.get(i);

            if (currentMedal.isNotEqualsAllMedal(previousMedal)) {
                nextRank = i + 1;
            }

            if (currentMedal.isEqualsNationCode(findNation)) {
                System.out.println(nextRank);
                return;
            }
        }
    }

    private static NationMedal createNationMedalFrom(BufferedReader reader) {
        try {
            return NationMedal.of(reader.readLine());
        } catch (IOException e) {
            return null;
        }
    }

    private static class NationMedal implements Comparable<NationMedal> {
        private final int nationCode;
        private final int goldMedalCount;
        private final int silverMedalCount;
        private final int bronzeMedalCount;

        private NationMedal(int nationCode, int goldMedalCount, int silverMedalCount, int bronzeMedalCount) {
            this.nationCode = nationCode;
            this.goldMedalCount = goldMedalCount;
            this.silverMedalCount = silverMedalCount;
            this.bronzeMedalCount = bronzeMedalCount;
        }

        private static int nextInteger(StringTokenizer tokenizer) {
            return Integer.parseInt(tokenizer.nextToken());
        }

        public static NationMedal of(String nationString) {
            StringTokenizer tokenizer = new StringTokenizer(nationString);
            return new NationMedal(nextInteger(tokenizer), nextInteger(tokenizer), nextInteger(tokenizer), nextInteger(tokenizer));
        }

        public boolean isEqualsNationCode(int nationCode) {
            return this.nationCode == nationCode;
        }

        public boolean isNotEqualsAllMedal(NationMedal other) {
            return !(this.goldMedalCount == other.goldMedalCount && this.silverMedalCount == other.silverMedalCount && this.bronzeMedalCount == other.bronzeMedalCount);
        }

        @Override
        public int compareTo(NationMedal o) {
            if (o.goldMedalCount != this.goldMedalCount) {
                return o.goldMedalCount - this.goldMedalCount;
            }

            if (o.silverMedalCount != this.silverMedalCount) {
                return o.silverMedalCount - this.silverMedalCount;
            }

            return o.bronzeMedalCount - this.bronzeMedalCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof NationMedal))
                return false;
            NationMedal that = (NationMedal) o;
            return nationCode == that.nationCode &&
                   goldMedalCount == that.goldMedalCount &&
                   silverMedalCount == that.silverMedalCount &&
                   bronzeMedalCount == that.bronzeMedalCount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(nationCode, goldMedalCount, silverMedalCount, bronzeMedalCount);
        }
    }
}
