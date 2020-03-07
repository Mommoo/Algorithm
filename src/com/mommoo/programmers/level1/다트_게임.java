package com.mommoo.programmers.level1;

import java.util.ArrayList;
import java.util.List;

public class 다트_게임 {
    public static void main(String[] args) {
        System.out.println(new 다트_게임().solution("1S2D*3T"));
        System.out.println(new 다트_게임().solution("1D2S#10S"));
        System.out.println(new 다트_게임().solution("1D2S0T"));
        System.out.println(new 다트_게임().solution("1S*2T*3S"));
        System.out.println(new 다트_게임().solution("1D#2S*3S"));
        System.out.println(new 다트_게임().solution("1T2D3D#"));
        System.out.println(new 다트_게임().solution("1D2S3T*"));
    }

    public int solution(String dartResult) {
        List<Expression> expressions = new ArrayList<>();
        NumberBuilder numberBuilder = new NumberBuilder();

        for (int idx = 0; idx < dartResult.length(); idx++) {
            char c = dartResult.charAt(idx);
            if (isNumber(c)) {
                numberBuilder.append(c);
                continue;
            }

            int number = numberBuilder.popNumber();
            Bonus bonus = Bonus.valueOf(c);
            boolean isOptionExist = isExistOption(dartResult, idx);
            Option option = isOptionExist ? Option.valueOf(dartResult.charAt(idx + 1)) : null;
            if (isOptionExist) {
                idx += 1;
            }
            expressions.add(new Expression(number, bonus, option));
        }

        return computeTotalScore(expressions);
    }

    private static boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isExistOption(String dartResult, int currentIndex) {
        int nextIndex = currentIndex + 1;
        if (nextIndex >= dartResult.length()) {
            return false;
        }

        return !isNumber(dartResult.charAt(nextIndex));
    }

    private static int computeTotalScore(List<Expression> expressions) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < expressions.size(); i++) {
            Expression expression = expressions.get(i);
            if (i != 0 && expression.isOptionStar()) {
                results.set(i - 1, results.get(i - 1) * 2);
            }
            results.add(expression.getScore());
        }

        return results.stream()
                      .mapToInt(Integer::intValue)
                      .sum();
    }

    private enum Bonus {
        S(1),
        D(2),
        T(3);

        private final int value;

        Bonus(int value) {
            this.value = value;
        }

        public static Bonus valueOf(char c) {
            return valueOf(String.valueOf(c));
        }

        public int apply(int score) {
            return (int) Math.pow(score, value);
        }
    }

    private enum Option {
        STAR,
        ACHAR;

        public static Option valueOf(char c) {
            if (c == '*') {
                return STAR;
            }

            return ACHAR;
        }
    }

    private static class NumberBuilder {
        private final StringBuilder builder = new StringBuilder();

        public void append(char number) {
            builder.append(number);
        }

        public int popNumber() {
            int number = Integer.parseInt(builder.toString());
            builder.setLength(0);
            return number;
        }
    }

    private static class Expression {
        private final int score;
        private final Bonus bonus;
        private final Option option;

        public Expression(int score, Bonus bonus, Option option) {
            this.score = score;
            this.bonus = bonus;
            this.option = option;
        }

        public int getScore() {
            int score = bonus.apply(this.score);
            if (isOptionStar()) {
                score *= 2;
            } else if (isOptionAchar()) {
                score *= -1;
            }
            return score;
        }

        public boolean isOptionStar() {
            return Option.STAR == option;
        }

        public boolean isOptionAchar() {
            return Option.ACHAR == option;
        }
    }
}
