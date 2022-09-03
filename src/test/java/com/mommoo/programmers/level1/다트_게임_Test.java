package com.mommoo.programmers.level1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class 다트_게임_Test {
    @DisplayName("case1")
    @ParameterizedTest
    @CsvSource("1S2D*3T, 37")
    void testCase1(String dartResult, int answer) {
        assertThat(solution(dartResult)).isEqualTo(answer);
    }

    @DisplayName("case2")
    @ParameterizedTest
    @CsvSource("1D2S#10S, 9")
    void testCase2(String dartResult, int answer) {
        assertThat(solution(dartResult)).isEqualTo(answer);
    }

    @DisplayName("case3")
    @ParameterizedTest
    @CsvSource("1D2S0T, 3")
    void testCase3(String dartResult, int answer) {
        assertThat(solution(dartResult)).isEqualTo(answer);
    }

    @DisplayName("case4")
    @ParameterizedTest
    @CsvSource("1S*2T*3S, 23")
    void testCase4(String dartResult, int answer) {
        assertThat(solution(dartResult)).isEqualTo(answer);
    }

    @DisplayName("case5")
    @ParameterizedTest
    @CsvSource("1D#2S*3S, 5")
    void testCase5(String dartResult, int answer) {
        assertThat(solution(dartResult)).isEqualTo(answer);
    }

    @DisplayName("case6")
    @ParameterizedTest
    @CsvSource("1T2D3D#, -4")
    void testCase6(String dartResult, int answer) {
        assertThat(solution(dartResult)).isEqualTo(answer);
    }

    @DisplayName("case7")
    @ParameterizedTest
    @CsvSource("1D2S3T*, 59")
    void testCase7(String dartResult, int answer) {
        assertThat(solution(dartResult)).isEqualTo(answer);
    }

    public static int solution(String dartResult) {
        // 자료의 가공 (게임 결과 나누기)
        List<String> dartResultElements = computeDartResultElements(dartResult);

        // 자료의 가공 (스코어 미리 넣어놓기)
        List<Integer> scores = computeScores(dartResultElements);

        // 자료의 처리 (스코어에 적용하기)
        int currentIndex = 0;
        for (String resultElem: dartResultElements) {
            for (int i = 0; i < resultElem.length(); i++) {
                char c = resultElem.charAt(i);
                if (Character.isDigit(c)) {
                    continue;
                }

                if (c == 'D' || c == 'T') {
                    int powNum = c == 'D' ? 2 : 3;
                    scores.set(currentIndex, (int)Math.pow(scores.get(currentIndex), powNum));
                    continue;
                }

                if (c == '*') {
                    scores.set(currentIndex, scores.get(currentIndex) * 2);
                    if (currentIndex > 0) {
                        scores.set(currentIndex - 1, scores.get(currentIndex-1) * 2);
                    }
                    continue;
                }

                if (c == '#') {
                    scores.set(currentIndex, scores.get(currentIndex) * -1);
                }
            }

            currentIndex++;
        }


        // 자료의 처리 (스코어 합 구하기)
        return sum(scores);
    }

    private static List<String> computeDartResultElements(String dartResult) {
        List<String> dartResultElements = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);
            if (Character.isDigit(c)) {
                if (builder.length() > 0) {
                    dartResultElements.add(builder.toString());
                }

                builder.setLength(0);
                builder.append(c).append(dartResult.charAt(i + 1));
                i++;
                continue;
            }

            builder.append(c);
        }

        if (builder.length() > 0) {
            dartResultElements.add(builder.toString());
        }

        return dartResultElements;
    }

    private static List<Integer> computeScores(List<String> dartResultElements) {
        List<Integer> scores = new ArrayList<>();

        StringBuilder numbers = new StringBuilder();
        for (String resultElem: dartResultElements) {
            for (int i = 0; i < resultElem.length(); i++) {
                char c = resultElem.charAt(i);
                if (Character.isDigit(c)) {
                    numbers.append(c);
                    char nextC = resultElem.charAt(i+1);
                    if (Character.isDigit(nextC)) {
                        numbers.append(nextC);
                        i++;
                    }

                    scores.add(Integer.parseInt(numbers.toString()));
                }

                numbers.setLength(0);
            }
        }

        return scores;
    }

    private static int sum(List<Integer> numbers) {
        int sum = 0;
        for (int num: numbers) {
            sum += num;
        }

        return sum;
    }
}
