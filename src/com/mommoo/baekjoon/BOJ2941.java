package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ2941 {
    public static void main(String[] args) throws Exception {
        String sentence = new BufferedReader(new InputStreamReader(System.in)).readLine();

        int count = 0;
        int nextIndex = 0;
        int len = sentence.length();
        Set<String> croatiaTable = createCroatiaTable();
        while (nextIndex < len) {
            if (nextIndex + 3 <= len && croatiaTable.contains(sentence.substring(nextIndex, nextIndex + 3))) {
                nextIndex += 3;
            } else if (nextIndex + 2 <= len && croatiaTable.contains(sentence.substring(nextIndex, nextIndex + 2))) {
                nextIndex += 2;
            } else {
                nextIndex++;
            }

            count++;
        }
        System.out.println(count);
    }

    private static Set<String> createCroatiaTable() {
        Set<String> croatiaTable = new HashSet<>();
        croatiaTable.add("c=");
        croatiaTable.add("c-");
        croatiaTable.add("dz=");
        croatiaTable.add("d-");
        croatiaTable.add("lj");
        croatiaTable.add("nj");
        croatiaTable.add("s=");
        croatiaTable.add("z=");

        return croatiaTable;
    }
}
