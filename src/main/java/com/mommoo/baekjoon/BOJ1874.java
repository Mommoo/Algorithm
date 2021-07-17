package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

public class BOJ1874 {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Queue<Integer> numbers = new LinkedList<>();
        while (N-- > 0) {
            numbers.offer(Integer.parseInt(reader.readLine()));
        }

        Stack<Integer> stack = new Stack<>();
        int increaseNumber = 1;
        while (!numbers.isEmpty()) {
            Integer peekNumber = stack.isEmpty() ? -1 : stack.peek();
            Integer nextNumber = numbers.peek();

            if (!Objects.equals(peekNumber, nextNumber)) {
                if (peekNumber > nextNumber) {
                    stack.pop();
                } else if (increaseNumber > nextNumber) {
                    writeNo();
                    break;
                } else {
                    stack.push(increaseNumber);
                    writePush();
                    increaseNumber++;
                }
                continue;
            }

            stack.pop();
            numbers.poll();
            writePop();

            if (numbers.isEmpty()) {
                break;
            }
        }

        System.out.println(BUILDER);
    }

    private static void writePush() {
        writeLine("+");
    }

    private static void writePop() {
        writeLine("-");
    }

    private static void writeNo() {
        BUILDER.setLength(0);
        BUILDER.append("NO");
    }

    private static void writeLine(String message) {
        BUILDER.append(message).append("\n");
    }

}
