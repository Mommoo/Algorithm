package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ4949 {
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String NEW_LINE = "\n";
    private static final Character SMALL_SOCKET = '(';
    private static final Character BIG_SOCKET = '[';

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        while (true) {
            String line = reader.readLine();
            if (line.equals(".")) {
                break;
            }
            builder.append(isBalanceString(line) ? YES : NO).append(NEW_LINE);
        }

        reader.close();
        System.out.println(builder);
    }

    public static boolean isBalanceString(String string) {
        Stack<Character> socketOrders = new Stack<>();

        for (int i = 0 ; i < string.length() ; i++) {
            char c = string.charAt(i);
            if (c == SMALL_SOCKET || c == BIG_SOCKET) {
                socketOrders.push(c);
            } else if (c == ')' && (socketOrders.isEmpty() || socketOrders.pop().equals(BIG_SOCKET))) {
                return false;
            } else if (c == ']' && (socketOrders.isEmpty() || socketOrders.pop().equals(SMALL_SOCKET))) {
                return false;
            }
        }

        return socketOrders.isEmpty();
    }
}
