package com.mommoo.level2;

import java.util.Arrays;

public class 폰켓몬 {
    public static void main(String[] args) {
        int[] nums1 = {3, 1, 2, 3};
        int[] nums2 = {3, 3, 3, 2, 2, 4};
        int[] nums3 = {3, 3, 3, 2, 2, 2};

        System.out.println(new 폰켓몬().solution(nums1));
        System.out.println(new 폰켓몬().solution(nums2));
        System.out.println(new 폰켓몬().solution(nums3));
    }

    public int solution(int[] nums) {
        return (int) Math.min(nums.length / 2, Arrays.stream(nums).distinct().count());
    }


}
