package com.mommoo.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ4641 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        while (!(line = br.readLine()).equals("-1")) {
            line = line.substring(0, line.length() - 2);
            StringTokenizer tokenizer = new StringTokenizer(line);
            List<Integer> nums = new ArrayList<>(20);
            while (tokenizer.hasMoreTokens()) {
                nums.add(Integer.parseInt(tokenizer.nextToken()));
            }

            Collections.sort(nums);
            int count = 0;
            for (int i = 0; i < nums.size(); i++) {
                int targetNum = nums.get(i);
                for (int j = i; j < nums.size(); j++) {
                    if (targetNum * 2 == nums.get(j)) {
                        count++;
                        break;
                    }

                    if (targetNum * 2 < nums.get(j)) {
                        break;
                    }
                }
            }
            bw.write(String.valueOf(count));
            bw.newLine();
        }

        bw.close();
        br.close();
    }
}
