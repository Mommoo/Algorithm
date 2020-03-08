package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1350 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer fileSizes = new StringTokenizer(reader.readLine());
        long clusterSize = Long.parseLong(reader.readLine());
        reader.close();

        long totalCount = 0;

        while (N -- > 0) {
            long fileSize = Long.parseLong(fileSizes.nextToken());
            if (fileSize == 0) {
                continue;
            } else if (fileSize % clusterSize == 0) {
                totalCount += fileSize / clusterSize;
                continue;
            }

            totalCount += (fileSize / clusterSize) + 1;
        }

        long totalSize = totalCount * clusterSize;
        System.out.println(totalSize);
    }
}
