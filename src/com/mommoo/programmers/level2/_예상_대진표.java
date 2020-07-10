package com.mommoo.programmers.level2;

public class _예상_대진표 {
    public static void main(String[] args) {
        System.out.println(new _예상_대진표().solution((int) Math.pow(2, 20), 1, (int) Math.pow(2, 20)));
        System.out.println(new _예상_대진표().solution(8, 3, 4));
        System.out.println(new _예상_대진표().solution(8, 1, 4));
        System.out.println(new _예상_대진표().solution(8, 7, 4));
    }

    public int solution(int n, int a, int b) {
        int answer = 1;
        Player playerA = new Player(a);
        Player playerB = new Player(b);
        while (!playerA.isMatched(playerB)) {
            playerA = playerA.next();
            playerB = playerB.next();
            answer++;
        }
        return answer;
    }

    private static class Player {
        private final int order;
        private final boolean isLeft;

        public Player(int order) {
            this.order = order;
            this.isLeft = order % 2 != 0;
        }

        public boolean isMatched(Player other) {
            return this.computeLeftOrder() == other.computeLeftOrder();
        }

        private int computeLeftOrder() {
            if (this.isLeft) {
                return this.order;
            }

            return this.order - 1;
        }

        public Player next() {
            int nextOrder = (computeLeftOrder() / 2) + 1;
            return new Player(nextOrder);
        }
    }
}
