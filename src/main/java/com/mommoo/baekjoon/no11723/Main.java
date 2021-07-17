package com.mommoo.baekjoon.no11723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        NumberSet numberSet = new NumberSet();
        StringBuilder builder = new StringBuilder();

        int operationCount = Integer.parseInt(reader.readLine());
        while (operationCount -- > 0) {
            String command = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(command);

            String operation = tokenizer.nextToken();
            int operand = tokenizer.hasMoreTokens() ? Integer.parseInt(tokenizer.nextToken()) : -1;
            switch(operation.substring(0, 2)) {
                case "ad":
                    numberSet.add(operand);
                    break;
                case "re":
                    numberSet.remove(operand);
                    break;
                case "ch":
                    int check = numberSet.check(operand);
                    builder.append(check).append("\n");
                    break;
                case "to":
                    numberSet.toggle(operand);
                    break;
                case "al":
                    numberSet.all();
                    break;
                case "em":
                    numberSet.empty();
                    break;
                default:
                    break;
            }
        }

        System.out.println(builder);
    }

    private static class NumberSet {
        private final boolean[] numberChecks = new boolean[20];

        public void add(int number) {
            int index = number - 1;
            numberChecks[index] = true;
        }

        public void remove(int number) {
            int index = number - 1;
            numberChecks[index] = false;
        }

        public int check(int number) {
            int index = number - 1;
            return numberChecks[index] ? 1 : 0;
        }

        public void toggle(int number) {
            int index = number - 1;
            numberChecks[index] = !numberChecks[index];
        }

        public void all() {
            Arrays.fill(numberChecks, true);
        }

        public void empty() {
            Arrays.fill(numberChecks, false);
        }
    }
}
