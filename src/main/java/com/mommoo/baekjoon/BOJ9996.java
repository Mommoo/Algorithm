package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9996 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Pattern pattern = new Pattern(reader.readLine());
        StringBuilder builder = new StringBuilder();

        while (N -- > 0) {
            builder.append(pattern.matched(reader.readLine()) ? "DA" : "NE")
                   .append(System.lineSeparator());
        }

        System.out.println(builder);
        reader.close();
    }

    private static class Pattern {
        private final String prefix;
        private final String postFix;

        public Pattern(String pattern) {
            int asteriskIndex = pattern.indexOf('*');
            this.prefix = pattern.substring(0, asteriskIndex);
            this.postFix = pattern.substring(asteriskIndex + 1);
        }

        public boolean matched(String string) {
            return string.length() >= postFix.length() + prefix.length() &&
                   string.startsWith(prefix) &&
                   string.endsWith(postFix);
        }
    }
}
