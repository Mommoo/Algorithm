package com.mommoo.level2;

import java.util.Arrays;
import java.util.List;

public class 다음_큰_숫자 {
    public static void main(String[] args) {
        System.out.println(new 다음_큰_숫자().solution(78));
        System.out.println(new 다음_큰_숫자().solution(15));
        System.out.println(new 다음_큰_숫자().solution(2));
    }

    public int solution(int n) {
//        Number number = Number.from(n);
//        return number.next();
        return NumberGenerator.from(n).nextNumber();
    }

    private static class Number {
        private final String binaryNumber;

        public Number(String binaryNumber) {
            this.binaryNumber = binaryNumber;
        }

        public static Number from(int number) {
            String binaryNumber = Integer.toBinaryString(number);
            return new Number(binaryNumber);
        }

        public int next() {
            List<Integer> indexAndOneCount = findIndexAndOneCount();
            int index = indexAndOneCount.get(0);
            String prefixBinaryString;
            int digitSize;

            if (index == -1) {
                prefixBinaryString = "";
                digitSize = binaryNumber.length() + 1;
            } else {
                prefixBinaryString = binaryNumber.substring(0, index);
                digitSize = binaryNumber.length() - index;
            }

            int oneCount = indexAndOneCount.get(1);
            String shiftedNumber = shiftNextNumber(digitSize, oneCount);

            return Integer.valueOf(prefixBinaryString + shiftedNumber, 2);
        }

        private List<Integer> findIndexAndOneCount() {
            int oneCount = 0;
            int idx = binaryNumber.length() - 1;
            for (; idx >= 0 ; idx--) {
                char c = binaryNumber.charAt(idx);
                if (c == '0' &&  oneCount != 0) {
                    break;
                }

                if (c == '1') {
                    oneCount++;
                }
            }

            return Arrays.asList(idx, oneCount);
        }

        private static String shiftNextNumber(int digitSize, int oneCount) {
            char[] digits = new char[digitSize];
            Arrays.fill(digits, '0');

            digits[0] = '1';
            oneCount--;

            Arrays.fill(digits, digitSize - oneCount, digitSize, '1');

            return new String(digits);
        }
    }

    private static class NumberGenerator {
        private final int bitCount;
        private int number;

        public NumberGenerator(int bitCount, int number) {
            this.bitCount = bitCount;
            this.number = number;
        }

        public static NumberGenerator from(int number) {
            return new NumberGenerator(Integer.bitCount(number), number);
        }

        public int nextNumber() {
            while (bitCount != Integer.bitCount(++number)) {

            }

            return number;
        }
    }
}
