package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BOJ6603 {
    private static final StringBuilder ANSWER_BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nextLine;
        while (!(nextLine = reader.readLine()).equals("0")) {
            String[] group = nextLine.substring(nextLine.indexOf(' ') + 1).split(" ");
            writeAllConditions(group, 0, new LinkedList<>());
            ANSWER_BUILDER.append("\n");
        }

        System.out.println(ANSWER_BUILDER);
    }

    private static void writeAllConditions(String[] group, int beginIndex, List<String> elements) {
        if (elements.size() == 6) {
            String answerLine = String.join(" ", elements);
            ANSWER_BUILDER.append(answerLine)
                          .append("\n");
            return;
        }

        for (; beginIndex < group.length; beginIndex++) {
            if (group.length - beginIndex < 6 - elements.size()) {
                return;
            }

            List<String> newElements = new LinkedList<>(elements);
            newElements.add(group[beginIndex]);
            writeAllConditions(group, beginIndex + 1, newElements);
        }
    }
}
