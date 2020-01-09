package com.mommoo.level1;

import java.util.HashSet;
import java.util.Set;

public class 약수의_합 {
    public static void main(String[] args) {
        System.out.println(new 약수의_합().solution(0));
        System.out.println(new 약수의_합().solution(12));
        System.out.println(new 약수의_합().solution(5));
    }

    public int solution(int n) {
        return new Factor(n).sum();
    }

    private static class Factor {
        private Set<Integer> elements = new HashSet<>();
        private int lastNum = Integer.MAX_VALUE;

        public Factor(int num) {
            if (num == 0) {
                return;
            }

            int startNum = 1;
            while (hasMore(startNum)) {
                addIfElement(num, startNum++);
            }
        }

        private boolean hasMore(int elementNum) {
            return elementNum < this.lastNum;
        }

        private void addIfElement(int num, int elementNum) {
            boolean isElement = num % elementNum == 0;
            if (!isElement) {
                return;
            }

            elements.add(elementNum);
            this.lastNum = num / elementNum;
            elements.add(this.lastNum);
        }

        private int sum() {
            return this.elements.stream()
                                .mapToInt(Integer::intValue)
                                .sum();
        }
    }
}
