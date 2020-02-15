package com.mommoo.programmers.level3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 최고의_집합 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 최고의_집합().solution(2, 9)));
        System.out.println(Arrays.toString(new 최고의_집합().solution(2, 1)));
        System.out.println(Arrays.toString(new 최고의_집합().solution(2, 8)));
    }

    public int[] solution(int n, int s) {
        return new MaxMultiplySet(n, s).toArray();
    }

    private static class MaxMultiplySet {
        private final List<Integer> elements;

        public MaxMultiplySet(int count, int sum) {
            if (count > sum) {
                this.elements = Arrays.asList(-1);
                return;
            }

            this.elements = createMaxMultiplyGroups(count, sum);
        }

        private static List<Integer> createMaxMultiplyGroups(int count, int sum) {
            int remain = sum % count;
            int baseNum = sum / count;

            LinkedList<Integer> elements = new LinkedList<>();
            while (count -- > 0) {
                int value = remain-- > 0 ? baseNum + 1 : baseNum;
                elements.addFirst(value);
            }
//            LinkedList<Integer> elements = IntStream.range(0, count)
//                                                    .mapToObj(idx -> baseNum)
//                                                    .collect(Collectors.toCollection(LinkedList::new));
//            while (remain-- > 0) {
//                Integer increasedNumber = elements.pollFirst() + 1;
//                elements.addLast(increasedNumber);
//            }

            return elements;
        }

        public int[] toArray() {
            int size = elements.size();
            int[] nums = new int[size];

            int index = 0;
            for (int value: elements) {
                nums[index++] = value;
            }

            return nums;
        }
    }
}
