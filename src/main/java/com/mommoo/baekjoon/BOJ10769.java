package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ10769 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sentence = reader.readLine();
        int happy = 0;
        int sad = 0;
        int beginIndex = 0;
        while (beginIndex < sentence.length()) {
            int findIndex = sentence.indexOf(":-", beginIndex);
            if (findIndex == -1) {
                break;
            }

            if (findIndex + 3 <= sentence.length()) {
                switch(sentence.charAt(findIndex + 2)) {
                    case ')':
                        happy++;
                        break;
                    case '(':
                        sad++;
                        break;
                    default:
                        break;
                }
            }

            beginIndex = findIndex + 3;
        }

        System.out.println(resolveStatusMessage(happy, sad));

        reader.close();
    }

    private static String resolveStatusMessage(int happy, int sad) {
        if (happy == 0 && sad == 0) {
            return "none";
        } else if (happy == sad) {
            return "unsure";
        } else if (happy > sad) {
            return "happy";
        } else {
            return "sad";
        }
    }
}
