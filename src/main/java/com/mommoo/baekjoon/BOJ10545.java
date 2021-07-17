package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ10545 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<Integer, Integer> keyPadConverter = new HashMap<>();
        Map<Integer, String> letterGroups = createLetterGroups();

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int beginKeyPadNumber = 1;
        while (tokenizer.hasMoreTokens()) {
            keyPadConverter.put(Integer.parseInt(tokenizer.nextToken()), beginKeyPadNumber++);
        }

        String word = br.readLine();

        for (int i = 0 ; i < word.length() ; i++) {
            char c = word.charAt(i);
            int originKeyPad = convertOriginKeyPad(c);
            int newKeyPad = keyPadConverter.get(originKeyPad);
            String letterGroup = letterGroups.get(originKeyPad);

            int moveCount = -1;
            do {
                moveCount++;
                bw.write(String.valueOf(newKeyPad));
            } while (letterGroup.charAt(moveCount) != c);

            if (i != word.length() - 1 && convertOriginKeyPad(word.charAt(i + 1)) == originKeyPad) {
                bw.write("#");
            }
        }

        br.close();
        bw.close();
    }

    private static int convertOriginKeyPad(char alphabet) {
        switch (alphabet) {
            case 'a': case 'b': case 'c':
                return 2;
            case 'd': case 'e': case 'f':
                return 3;
            case 'g': case 'h': case 'i':
                return 4;
            case 'j': case 'k': case 'l':
                return 5;
            case 'm': case 'n': case 'o':
                return 6;
            case 'p': case 'q': case 'r': case 's':
                return 7;
            case 't': case 'u': case 'v':
                return 8;
            case 'w': case 'x': case 'y': case 'z':
                return 9;
        }

        return -1;
    }

    private static Map<Integer, String> createLetterGroups() {
        Map<Integer, String> letterGroups = new HashMap<>();
        letterGroups.put(2, "abc");
        letterGroups.put(3, "def");
        letterGroups.put(4, "ghi");
        letterGroups.put(5, "jkl");
        letterGroups.put(6, "mno");
        letterGroups.put(7, "pqrs");
        letterGroups.put(8, "tuv");
        letterGroups.put(9, "wxyz");
        return letterGroups;
    }
}
