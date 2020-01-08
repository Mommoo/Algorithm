package com.mommoo.level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class 숫자_야구 {
    public static void main(String[] args) {
        int[][] baseball = new int[][] {
                new int[] {123, 1, 1},
                new int[] {356, 1, 0},
                new int[] {327, 2, 0},
                new int[] {489, 0, 1}
        };
        System.out.println(new 숫자_야구().solution(baseball));
    }

    public int solution(int[][] baseball) {
        int count = 0;

        BaseBallNumberGenerator generator = new BaseBallNumberGenerator();
        BaseBallConditions conditions = BaseBallConditions.from(baseball);

        while (generator.hasMore()) {
            String baseBallNumber = generator.next();
            if (conditions.isMatched(baseBallNumber)) {
                count ++;
            }
        }

        return count;
    }

    private static class BaseBallNumberGenerator {
        private static final int MAX_NUMBER = 987;

        private int nextNumber = 123;
        private Set<Integer> duplicateInspector = new HashSet<>();

        public boolean hasMore() {
            return this.nextNumber <= MAX_NUMBER;
        }

        public String next() {
            for (int number = nextNumber; number <= MAX_NUMBER; number++) {
                if (isValidNumber(number)) {
                    this.nextNumber = number + 1;
                    return String.valueOf(number);
                }
            }

            return "";
        }

        private boolean isValidNumber(int number) {
            duplicateInspector.clear();

            String.valueOf(number)
                  .chars()
                  .filter(cNum -> cNum != '0')
                  .forEach(duplicateInspector::add);

            return duplicateInspector.size() == 3;
        }

    }

    private static class BaseBallConditions {
        private final List<BaseBallCondition> conditions;

        private BaseBallConditions(List<BaseBallCondition> conditions) {
            this.conditions = conditions;
        }

        public static BaseBallConditions from(int[][] baseballData) {
            List<BaseBallCondition> conditions = Arrays.stream(baseballData)
                                                       .map(BaseBallCondition::from)
                                                       .collect(Collectors.toList());

            return new BaseBallConditions(conditions);
        }

        public boolean isMatched(String baseBallNumbers) {
            return conditions.stream().allMatch(condition -> condition.isValid(baseBallNumbers));
        }
    }

    private static class BaseBallCondition {
        private final int[] numberElements;
        private final Set<Integer> numberFinder;
        private final BaseBallMatcher baseBallMatcher;

        private BaseBallCondition(int[] numberElements, int basicStrike, int basicBall) {
            this.numberElements = numberElements;
            this.baseBallMatcher = new BaseBallMatcher(basicStrike, basicBall);
            this.numberFinder = createNumberFinder(numberElements);
        }

        private static Set<Integer> createNumberFinder(int[] numberElements) {
            Set<Integer> numberFinder = new HashSet<>();
            for (int numberElement : numberElements) {
                numberFinder.add(numberElement);
            }
            return numberFinder;
        }

        public static BaseBallCondition from(int[] data) {
            return new BaseBallCondition(computeNumberElements(data[0]), data[1], data[2]);
        }

        private static int[] computeNumberElements(int numbers) {
            return String.valueOf(numbers).chars()
                         .map(c -> c - '0')
                         .toArray();
        }

        public boolean isValid(String baseBallNumbers) {
            baseBallMatcher.clear();

            for (int i = 0; i < 3; i++) {
                int targetNum = baseBallNumbers.charAt(i) - '0';
                int thisNum = numberElements[i];
                if (targetNum == thisNum) {
                    baseBallMatcher.increaseStrike();
                } else if (numberFinder.contains(targetNum)) {
                    baseBallMatcher.increaseBall();
                }
            }

            return baseBallMatcher.isMatched();
        }

    }

    private static class BaseBallMatcher {
        private final int basicStrike;
        private final int basicBall;
        private int strikeCount;
        private int ballCount;

        public BaseBallMatcher(int basicStrike, int basicBall) {
            this.basicStrike = basicStrike;
            this.basicBall = basicBall;
        }

        public void clear() {
            this.strikeCount = this.ballCount = 0;
        }

        public void increaseStrike() {
            strikeCount++;
        }

        public void increaseBall() {
            ballCount++;
        }

        public boolean isMatched() {
            return this.strikeCount == this.basicStrike && this.ballCount == this.basicBall;
        }
    }
}
