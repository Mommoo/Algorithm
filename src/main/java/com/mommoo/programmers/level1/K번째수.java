package com.mommoo.programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class K번째수 {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {
                {2, 5, 3},
                {4, 4, 1},
                {1, 7, 3}
        };
        K번째수 instance = new K번째수();
        int[] answer = instance.solution(array, commands);
        System.out.println(Arrays.toString(answer));
    }

    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();
        for (int[] command : commands) {
            int len = command[1] - command[0] + 1;
            int[] arr = new int[len];
            System.arraycopy(array, command[0] - 1, arr, 0, len);
            Arrays.sort(arr);
            answer.add(arr[command[2] - 1]);
        }

        return answer.stream()
                     .mapToInt(x -> x)
                     .toArray();
    }
}
