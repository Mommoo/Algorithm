package com.mommoo.programmers.level1;

import java.util.Objects;

public class 키패드_누르기 {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        System.out.println(new 키패드_누르기().solution(numbers, "right"));

        int[] numbers2 = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        System.out.println(new 키패드_누르기().solution(numbers2, "left"));

        int[] numbers3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(new 키패드_누르기().solution(numbers3, "right"));
    }

    private static final char LEFT_HAND = 'L';
    private static final char RIGHT_HAND = 'R';
    private static final int KEYPAD_STAR = 10;
    private static final int KEYPAD_SHARP = 12;

    private static final int[] DIRECTION_KEYPAD_NUMBER_VALUES = {-1, -3, 3, 1};

    public String solution(int[] numbers, String hand) {
        boolean isDefaultLeft = Objects.equals(hand, "left");
        StringBuilder useHands = new StringBuilder();

        int lastLeftNumber = KEYPAD_STAR;
        int lastRightNumber = KEYPAD_SHARP;

        for (int number: numbers) {
            if (isLeftHand(number)) {
                lastLeftNumber = number;
                useHands.append(LEFT_HAND);
                continue;
            }

            if (isRightHand(number)) {
                lastRightNumber = number;
                useHands.append(RIGHT_HAND);
                continue;
            }

            if (computeIsLeftHand(number, lastLeftNumber, lastRightNumber, isDefaultLeft)) {
                lastLeftNumber = number;
                useHands.append(LEFT_HAND);
                continue;
            }

            lastRightNumber = number;
            useHands.append(RIGHT_HAND);
        }

        return useHands.toString();
    }

    private static boolean isLeftHand(int number) {
        return number == 1 || number == 4 || number == 7;
    }

    private static boolean isRightHand(int number) {
        return number == 3 || number == 6 || number == 9;
    }

    private static boolean computeIsLeftHand(int number, int lastLeftNumber, int lastRightNumber, boolean defaultLeft) {
        int leftHandMoveCount = nextKeypadNumber(lastLeftNumber, number);
        int rightHandMoveCount = nextKeypadNumber(lastRightNumber, number);

        if (leftHandMoveCount == rightHandMoveCount) {
            return defaultLeft;
        }

        return leftHandMoveCount < rightHandMoveCount;
    }

    private static int nextKeypadNumber(int beginNumber, int goalNumber) {
        int endGoalNumber = goalNumber == 0 ? 11 : goalNumber;
        int startNumber = beginNumber == 0 ? 11 : beginNumber;
        return nextKeypadNumber(startNumber, endGoalNumber, 0);
    }

    private static int nextKeypadNumber(int currentNumber, int goalNumber, int moveCount) {
        if (currentNumber == goalNumber) {
            return moveCount;
        }

        int minDiffMoveKeypadNumber = Integer.MAX_VALUE;
        for (int directionKeypadNumberValue: DIRECTION_KEYPAD_NUMBER_VALUES) {
            int moveKeypadNumber = computeMoveKeypadNumber(currentNumber, directionKeypadNumberValue);
            int diffMoveKeypadNumber = Math.abs(goalNumber - moveKeypadNumber);

            if (diffMoveKeypadNumber < Math.abs(goalNumber - minDiffMoveKeypadNumber)) {
                minDiffMoveKeypadNumber = moveKeypadNumber;
            }
        }

        return nextKeypadNumber(minDiffMoveKeypadNumber, goalNumber, moveCount + 1);
    }

    private static int computeMoveKeypadNumber(int currentNumber, int computeValue) {
        int moveKeypadNumber = currentNumber + computeValue;
        if (0 < moveKeypadNumber && moveKeypadNumber <= 12) {
            return moveKeypadNumber;
        }

        return currentNumber;
    }
}
