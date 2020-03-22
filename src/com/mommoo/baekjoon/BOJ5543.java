package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ5543 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int minimumBurgerPrice = computeMinimumPrice(reader.readLine(), reader.readLine(), reader.readLine());
        int minimumBeveragePrice = computeMinimumPrice(reader.readLine(), reader.readLine());

        System.out.println(minimumBurgerPrice + minimumBeveragePrice - 50);
    }

    private static int computeMinimumPrice(String... prices) {
        return Arrays.stream(prices)
                     .mapToInt(Integer::parseInt)
                     .min()
                     .orElseThrow(() -> new RuntimeException(""));
    }
}
