package com.mommoo.programmers.level1;

public class 콜라츠_추측 {
    public static void main(String[] args) {
        System.out.println(new 콜라츠_추측().solution(6));
        System.out.println(new 콜라츠_추측().solution(16));
        System.out.println(new 콜라츠_추측().solution(626331));
    }

    public int solution(int num) {
        CollatzNumber collatzNumber = new CollatzNumber(num);

        while (collatzNumber.hasMore()) {
            collatzNumber.process();
        }

        return collatzNumber.getCount();
    }

    private static class CollatzNumber {
        private static final int MAX_COUNT = 500;
        private long value;
        private int count;

        public CollatzNumber(int number) {
            this.value = number;
        }

        private boolean hasMore() {
            return count < MAX_COUNT && this.value != 1;
        }

        private void process() {
            if (count == MAX_COUNT) {
                count = -1;
            }

            if (value % 2L == 0) {
                value = value / 2L;
            } else {
                value = (value * 3L) + 1L;
            }

            count++;
        }

        private int getCount() {
            if (count == MAX_COUNT) {
                return -1;
            }

            return count;
        }
    }
}
