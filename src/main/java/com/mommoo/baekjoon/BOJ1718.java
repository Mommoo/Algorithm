package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1718 {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sentence = reader.readLine();

        Queue<Character> secrets = new LinkedList<>();
        String secret = reader.readLine();
        for (int i = 0; i < secret.length(); i++) {
            secrets.offer(secret.charAt(i));
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            char nextSecretChar = secrets.poll();
            secrets.offer(nextSecretChar);
            builder.append(translate(c, nextSecretChar));
        }

        System.out.println(builder);
    }

    private static char translate(char c, char secretChar) {
        if (c == ' ') {
            return c;
        }

        int secretCharOrder = secretChar - 'a' + 1;
        int charOrder = c - 'a' + 1;
        if (secretCharOrder >= charOrder) {
            int remainOrder = secretCharOrder - charOrder;
            char nextSecretChar = (char)('a' + remainOrder - 1);
            return translate('z', nextSecretChar);
        }

        return (char)(c - secretCharOrder);
    }
}
