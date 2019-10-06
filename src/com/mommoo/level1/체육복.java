package com.mommoo.level1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 체육복 {
    private List<Integer> losts = new ArrayList<>();
    private Set<Integer> reserves = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(new 체육복().solution(5, new int[]{2, 4}, new int[]{1, 3, 5}));
        System.out.println(new 체육복().solution(5, new int[]{2, 4}, new int[]{3}));
        System.out.println(new 체육복().solution(3, new int[]{3}, new int[]{1}));
    }

    public int solution(int n, int[] lost, int[] reserve) {
        ready(lost, reserve);
        int possibleCount = n - losts.size();
        for (Integer x : losts) {
            if (reserves.contains(x - 1)) {
                reserves.remove(x - 1);
                possibleCount++;
            } else if (reserves.contains(x + 1)) {
                reserves.remove(x + 1);
                possibleCount++;
            }
        }

        return possibleCount;
    }

    private void ready(int[] losts, int[] reserves) {
        for (int reserve : reserves) {
            this.reserves.add(reserve);
        }

        for (int lost : losts) {
            if (this.reserves.contains(lost)) {
                this.reserves.remove(lost);
            } else {
                this.losts.add(lost);
            }
        }
    }
}
