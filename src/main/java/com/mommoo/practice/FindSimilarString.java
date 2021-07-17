package com.mommoo.practice;

import java.util.ArrayList;
import java.util.List;

public class FindSimilarString {
    private final String baseText;
    private final String findText;
    private final List<Integer> beginIndexes;
    private final boolean isRight;

    public FindSimilarString(String baseText, String findText, int[] answer) {
        this.baseText = baseText;
        this.findText = findText;
        this.beginIndexes = findSimilarCount(baseText, findText);

        for (int i = 0 ; i < beginIndexes.size(); i++) {
            int beginIndex = beginIndexes.get(i);
            if (answer[i] != beginIndex) {
                isRight = false;
                return;
            }
        }
        this.isRight = true;
    }

    public static void main(String[] args) {
        System.out.println(new FindSimilarString("abcabcdabcd", "abcd", new int[]{3, 7}));
        System.out.println(new FindSimilarString("abbaabbcbbe", "bbcbb", new int[]{5}));
    }

    private static List<Integer> findSimilarCount(String baseText, String findText) {
        List<Integer> beginIndexes = new ArrayList<>();
        int[] failureFunction = buildFailureFunction(findText);
        int len = baseText.length();
        for (int i = 0 ; i < len; i ++) {
            int beginIndex = 1;
            if (len - i >= findText.length() && baseText.charAt(i) == findText.charAt(0)) {
                for (; beginIndex < findText.length(); beginIndex++) {
                    if (baseText.charAt(i+beginIndex) != findText.charAt(beginIndex)) {
                        break;
                    }
                }
            }
            int sameLen = beginIndex;

            if (sameLen == findText.length()) {
                beginIndexes.add(i);
            }

            i += sameLen - failureFunction[sameLen - 1] - 1;
        }

        return beginIndexes;
    }

    private static int[] buildFailureFunction(String findText) {
        int size = findText.length();
        int[] failureFunction = new int[size];
        for (int i = 1 ; i < size ; i ++) {
            int previousIndex = i - 1;
            while (true) {
                int sameLen = failureFunction[previousIndex];
                int baseIndex = sameLen - 1;
                if (findText.charAt(baseIndex + 1) == findText.charAt(i)) {
                    failureFunction[i] = failureFunction[previousIndex] + 1;
                    break;
                }

                if (baseIndex == -1) {
                    break;
                }

                previousIndex = baseIndex;
            }
        }
        return failureFunction;
    }

    @Override
    public String toString() {
        return "FindSimilarString{" +
               "baseText='" + baseText + '\'' +
               ", findText='" + findText + '\'' +
               ", beginIndexes=" + beginIndexes +
               ", isRight=" + isRight +
               '}';
    }
}
