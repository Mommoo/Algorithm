package com.mommoo.level2;

public class 타겟_넘버 {
    public static void main(String[] args) {
        System.out.println(new 타겟_넘버().solution(new int[]{1, 1, 1, 1, 1}, 3));
    }

    public int solution(int[] numbers, int target) {
        return dfs(0, 0, target, numbers);
    }

    private int dfs(int nextIndex, int sum, int target, int[] numbers) {
        if (nextIndex == numbers.length) {
            return sum == target ? 1 : 0;
        }

        int count = 0;

        count += dfs(nextIndex + 1, sum + numbers[nextIndex], target, numbers);
        count += dfs(nextIndex + 1, sum - numbers[nextIndex], target, numbers);

        return count;
    }
}
