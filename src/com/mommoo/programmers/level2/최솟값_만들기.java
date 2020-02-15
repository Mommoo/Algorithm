package com.mommoo.programmers.level2;

import java.util.Arrays;

public class 최솟값_만들기 {
    public static void main(String[] args) {
        System.out.println(new 최솟값_만들기().solution(new int[]{1, 4, 2}, new int[]{5, 4, 4}));
        System.out.println(new 최솟값_만들기().solution(new int[]{1, 2}, new int[]{3, 4}));
    }

    public int solution(int[] A, int[] B) {
//        LinkedList<Integer> aList = Arrays.stream(A)
//                                          .boxed()
//                                          .sorted()
//                                          .collect(Collectors.toCollection(LinkedList::new));
//
//        LinkedList<Integer> bList = Arrays.stream(B)
//                                          .boxed()
//                                          .sorted(Comparator.reverseOrder())
//                                          .collect(Collectors.toCollection(LinkedList::new));

        Arrays.sort(A);
        Arrays.sort(B);

        int len = A.length;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i] * B[len - i - 1];
        }

        return sum;
    }
}
