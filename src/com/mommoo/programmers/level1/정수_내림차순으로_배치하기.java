package com.mommoo.programmers.level1;

public class 정수_내림차순으로_배치하기 {
    public static void main(String[] args) {
        System.out.println(new 정수_내림차순으로_배치하기().solution(118372L));
    }

    public long solution(long n) {
        int[] nums = createCountingSortedArray(n);
        return arrangeNumsByAscendingOrder(nums);
    }

    private static int[] createCountingSortedArray(long n) {
        int[] nums = new int[10];

        String.valueOf(n).chars()
              .map(cInt -> cInt - '0')
              .forEach(idx -> nums[idx]++);

        return nums;
    }

    private static long arrangeNumsByAscendingOrder(int[] nums) {
        StringBuilder builder = new StringBuilder();
        for (int index = 9; index >= 0 ; index--) {
            int count = nums[index];
            while (count-- > 0) {
                builder.append(index);
            }
        }

        return Long.parseLong(builder.toString());
    }

}
