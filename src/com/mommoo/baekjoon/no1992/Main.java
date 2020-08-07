package com.mommoo.baekjoon.no1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[][] image;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int imageSize = Integer.parseInt(reader.readLine());
        image = new int[imageSize][imageSize];
        for (int row = 0; row < imageSize; row++) {
            String imageLine = reader.readLine();
            for (int col = 0; col < imageSize; col++) {
                image[row][col] = imageLine.charAt(col) - '0';
            }
        }
        System.out.println(dfs(imageSize, 0, 0));
    }

    public static String dfs(int nextSize, int beginRow, int beginCol) {
        int singleCompressResult = computeSingleCompressResult(nextSize, beginRow, beginCol);
        if (singleCompressResult != -1) {
            return String.valueOf(singleCompressResult);
        }

        StringBuilder builder = new StringBuilder();

        if (nextSize == 2) {
            builder.append("(");
            for (int row = beginRow; row < beginRow + nextSize; row++) {
                for (int col = beginCol;col < beginCol + nextSize; col++) {
                    builder.append(image[row][col]);
                }
            }
            builder.append(")");
            return builder.toString();
        }


        builder.append("(");
        builder.append(dfs(nextSize / 2, beginRow, beginCol));
        builder.append(dfs(nextSize / 2, beginRow, beginCol + nextSize/2));
        builder.append(dfs(nextSize / 2, beginRow + nextSize/2, beginCol));
        builder.append(dfs(nextSize / 2, beginRow + nextSize/2, beginCol + nextSize/2));
        builder.append(")");
        return builder.toString();
    }

    private static int computeSingleCompressResult(int size, int beginRow, int beginCol) {
        int standardResult = image[beginRow][beginCol];
        for (int row = beginRow; row < beginRow + size; row++) {
            for (int col = beginCol; col < beginCol + size; col++) {
                if (standardResult != image[row][col]) {
                    return -1;
                }
            }
        }

        return standardResult;
    }
}
