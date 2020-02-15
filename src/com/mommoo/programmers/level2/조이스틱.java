package com.mommoo.programmers.level2;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 조이스틱 {
    public static void main(String[] args) {
        System.out.println(new 조이스틱().solution("JEROEN"));
        System.out.println(new 조이스틱().solution("JAN"));
    }

    public int solution(String name) {
        int totalCount = 0;

        CharacterProvider characterProvider = new CharacterProvider(name);
        while (characterProvider.hasMore()) {
            char c = characterProvider.next();
            totalCount += computeChangeCount(c);
        }

        return totalCount + characterProvider.getTotalMoveCount();
    }

    public static int computeChangeCount(char c) {
        int forwardChangeCount = c - 'A';
        int backwardChangeCount = 'Z' - c + 1;

        return Math.min(forwardChangeCount, backwardChangeCount);
    }

    private static class CharacterProvider {
        private final String name;
        private final LinkedList<Integer> indexes;
        private int position = 0;
        private int totalMoveCount = 0;

        public CharacterProvider(String name) {
            this.name = name;
            indexes = IntStream.range(0, name.length())
                               .filter(idx -> name.charAt(idx) != 'A')
                               .boxed()
                               .collect(Collectors.toCollection(LinkedList::new));
        }

        public char next() {
            this.position = popNextIndex();
            return name.charAt(position);
        }

        private int popNextIndex() {
            int forwardMoveCount = computeForwardMoveCount();
            int backwardMoveCount = computeBackwardMoveCount();

            if (forwardMoveCount > backwardMoveCount) {
                totalMoveCount += backwardMoveCount;
                return indexes.pollLast();
            } else {
                totalMoveCount += forwardMoveCount;
                return indexes.pollFirst();
            }
        }

        private int computeForwardMoveCount() {
            int firstIndex = indexes.getFirst();
            if (firstIndex < position) {
                return name.length() - position + firstIndex;
            } else {
                return firstIndex - position;
            }
        }

        private int computeBackwardMoveCount() {
            int lastIndex = indexes.getLast();
            if (position < lastIndex) {
                return name.length() - lastIndex + position;
            } else {
                return position - lastIndex;
            }
        }

        public int getTotalMoveCount() {
            return totalMoveCount;
        }

        private boolean hasMore() {
            return !indexes.isEmpty();
        }
    }
}
