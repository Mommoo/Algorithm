package com.mommoo.level1;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class 모의고사 {

    public static void main(String[] args) {
        int[] answer = {1, 2, 3, 4, 5};
        int[] results = new 모의고사().solution(answer);
        System.out.println(Arrays.toString(results));
    }

    private static int countAnswer(MathAnswer mathAnswer, int[] answer) {
        return (int) IntStream.of(answer)
                              .filter(mathAnswer::isRightAnswerAtNext)
                              .count();
    }

    private static int findMaxCount(List<Integer> counts) {
        return counts.stream()
                     .mapToInt(count -> count)
                     .max()
                     .orElse(-1);
    }

    public int[] solution(int[] answers) {
        MathAnswer firstMathHater = new MathAnswer(1, 2, 3, 4, 5);
        MathAnswer secondMathHater = new MathAnswer(2, 1, 2, 3, 2, 4, 2, 5);
        MathAnswer thirdMathHater = new MathAnswer(3, 3, 1, 1, 2, 2, 4, 4, 5, 5);

        List<Integer> counts = Stream.of(firstMathHater, secondMathHater, thirdMathHater)
                                     .map(mathHater -> countAnswer(mathHater, answers))
                                     .collect(Collectors.toList());

        int maxCount = findMaxCount(counts);

        return IntStream.range(0, 3)
                        .filter(order -> counts.get(order) == maxCount)
                        .map(x -> x + 1)
                        .toArray();
    }

    private static class MathAnswer {
        private final Queue<Integer> answerPatterns = new LinkedList<>();

        private MathAnswer(Integer... patterns) {
            Collections.addAll(answerPatterns, patterns);
        }

        private boolean isRightAnswerAtNext(Integer answer) {
            Integer nextAnswer = answerPatterns.poll();
            boolean isRightAnswer = Objects.equals(nextAnswer, answer);
            answerPatterns.offer(nextAnswer);
            return isRightAnswer;
        }
    }
}
