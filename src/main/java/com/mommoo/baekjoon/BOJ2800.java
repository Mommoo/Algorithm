package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BOJ2800 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();

        List<Bracket> brackets = searchBrackets(word);
        List<String> conditions = createConditions(brackets.size(), "", 0, createAllBracketExistCondition(brackets.size()));

        Set<String> results = new HashSet<>();
        Set<Integer> removeIndexes = new HashSet<>();
        StringBuilder builder = new StringBuilder();

        for (String condition: conditions) {
            removeIndexes.clear();
            builder.setLength(0);
            for (int i = 0; i < brackets.size(); i++) {
                char c = condition.charAt(i);
                if (c == '1') {
                    Bracket bracket = brackets.get(i);
                    removeIndexes.add(bracket.openIndex);
                    removeIndexes.add(bracket.closeIndex);
                }
            }

            for (int i = 0; i < word.length(); i++) {
                if (removeIndexes.contains(i)) {
                    continue;
                }

                builder.append(word.charAt(i));
            }

            results.add(builder.toString());
        }

        List<String> orderedResults = new ArrayList<>(results);
        Collections.sort(orderedResults);

        for (String result: orderedResults) {
            bw.write(result);
            bw.newLine();
        }

        bw.close();
        br.close();
    }

    private static List<Bracket> searchBrackets(String word) {
        List<Bracket> completedBrackets = new ArrayList<>(16);
        Stack<Bracket> brackets = new Stack<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '(') {
                brackets.push(new Bracket(i));
            } else if (c == ')') {
                Bracket bracket = brackets.pop();
                bracket.setCloseIndex(i);
                completedBrackets.add(bracket);
            }
        }

        return completedBrackets;
    }

    private static List<String> createConditions(int bracketSize, String result, int nextIndex, String exceptResult) {
        if (result.length() == bracketSize) {
            if (exceptResult.equals(result)) {
                return Collections.emptyList();
            }

            return Collections.singletonList(result);
        }

        List<String> results = new ArrayList<>();

        results.addAll(createConditions(bracketSize, result + 0, nextIndex + 1, exceptResult));
        results.addAll(createConditions(bracketSize, result + 1, nextIndex + 1, exceptResult));

        return results;
    }

    private static String createAllBracketExistCondition(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < size; i++) {
            builder.append("0");
        }
        return builder.toString();
    }

    private static class Bracket {
        private final int openIndex;
        private int closeIndex;

        public Bracket(int openIndex) {
            this.openIndex = openIndex;
        }

        public void setCloseIndex(int closeIndex) {
            this.closeIndex = closeIndex;
        }
    }
}
