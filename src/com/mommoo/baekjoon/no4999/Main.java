package com.mommoo.baekjoon.no4999;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String canShoutString = reader.readLine();
        String requestShoutString = reader.readLine();
        reader.close();

        System.out.println(canShoutString.length() >= requestShoutString.length() ? "go" : "no");
    }
}
