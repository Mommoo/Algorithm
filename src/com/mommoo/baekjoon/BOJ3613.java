package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String variableName = br.readLine();
        if (isJavaConvention(variableName)) {
            System.out.println(convertToCPlusPlus(variableName));
        } else if (isCPlusPlusConvention(variableName)){
            System.out.println(convertToJava(variableName));
        } else {
            System.out.println("Error!");
        }
    }

    public static boolean isJavaConvention(String variableName) {
        char first = variableName.charAt(0);
        if (Character.isUpperCase(first) || first == '_') {
            return false;
        }

        for (int i = 0; i < variableName.length(); i++) {
            char c = variableName.charAt(i);
            if (c == '_') {
                return false;
            }
        }

        return true;
    }

    public static boolean isCPlusPlusConvention(String variableName) {
        char first = variableName.charAt(0);
        char last = variableName.charAt(variableName.length() - 1);
        if (Character.isUpperCase(first) || first == '_' || last == '_') {
            return false;
        }

        for (int i = 0; i < variableName.length(); i++) {
            char c = variableName.charAt(i);
            if (c == '_' && variableName.charAt(i + 1) == '_') {
                return false;
            }

            if (Character.isUpperCase(c)) {
                return false;
            }
        }

        return true;
    }

    public static String convertToCPlusPlus(String javaConventionVariableName) {
        int len = javaConventionVariableName.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = javaConventionVariableName.charAt(i);
            if (Character.isUpperCase(c)) {
                builder.append("_").append(Character.toLowerCase(c));
            } else {
                builder.append(c);
            }
        }

        return builder.toString();
    }

    public static String convertToJava(String cConventionVariableName) {
        int len = cConventionVariableName.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < len ; i++) {
            char c = cConventionVariableName.charAt(i);
            if (c == '_') {
                builder.append(Character.toUpperCase(cConventionVariableName.charAt(i + 1)));
                i++;
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
